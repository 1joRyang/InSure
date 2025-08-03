/**
 * 실손보험 배정 시스템 웹소켓 알림 서비스
 */
var WebSocketService = {
    ws: null,
    isConnected: false,
    serverUrl: 'ws://localhost:8081',
    currentUser: null,
    heartbeatInterval: null,
    
	// 초기화
	init: function() {
	    this.loadUserInfoFromSession();
	    this.connect();
	    this.setupHeartbeat();
	},

	
	// 사용자 정보 로드
	   loadUserInfoFromSession: function() { 
	       let userKey = "default_user"; // 기본값 설정
	       let userInfo = { key: userKey, type: "NONE" }; 

	       let webSquareEmpNo = "";
	       if (typeof $p !== 'undefined' && $p.getSession) {
	           webSquareEmpNo = $p.getSession("empNo");
	       } else if (typeof WebSquare !== 'undefined' && WebSquare.session && WebSquare.session.get) {
	           webSquareEmpNo = WebSquare.session.get("empNo");
	       }
	       
	       if (webSquareEmpNo) {
	           userKey = webSquareEmpNo;
	           userInfo = { key: userKey, type: "EMPLOYEE" };
	           console.log("웹스퀘어 세션에서 로드된 직원번호:", userKey);
	           this.currentUser = { empNo: userKey, role: "EMPLOYEE" };
	           return; 
	       }

	       // localStorage에서 employee 정보 가져오기
	       try {
	           const employeeData = localStorage.getItem("employee");
	           if (employeeData) {
	               const employee = JSON.parse(employeeData);
	               if (employee && employee.empNo) {
	                   userKey = employee.empNo;
	                   userInfo = { key: userKey, type: "EMPLOYEE" };
	                   console.log("로컬스토리지(employee)에서 로드된 직원번호:", userKey);
	                   this.currentUser = employee; 
	                   return; 
	               }
	           }
	       } catch (error) {
	           console.error("로컬스토리지 'employee' 데이터 파싱 실패:", error);
	       }

	       // localStorage에서 simple 정보 가져오기
	       try {
	           const simpleData = localStorage.getItem("simple");
	           if (simpleData) {
	               const simple = JSON.parse(simpleData);
	               if (simple && simple.userId) { 
	                   userKey = simple.userId;
	                   userInfo = { key: userKey, type: "SIMPLE" };
	                   console.log("로컬스토리지(simple)에서 로드된 사용자 ID:", userKey);
	                   this.currentUser = { empNo: userKey, role: "SIMPLE" };
	                   return; 
	               }
	           }
	       } catch (error) {
	           console.error("로컬스토리지 'simple' 데이터 파싱 실패:", error);
	       }

	       // localStorage에서 user 정보 가져오기
	       try {
	           const userData = localStorage.getItem("user");
	           if (userData) {
	               const user = JSON.parse(userData);
	               if (user && user.userId) { 
	                   userKey = user.userId;
	                   userInfo = { key: userKey, type: "USER" };
	                   console.log("로컬스토리지(user)에서 로드된 사용자 ID:", userKey);
	                   this.currentUser = { empNo: userKey, role: "USER" };
	                   return; 
	               }
	           }
	       } catch (error) {
	           console.error("로컬스토리지 'user' 데이터 파싱 실패:", error);
	       }

	       // 용자 정보를 찾지 못한 경우
	       console.warn("세션 정보 (직원번호/사용자 ID)가 없어 기본값을 사용합니다.");
	       this.currentUser = { // 기본 사용자
	           empNo: "default_user",
	           empName: "기본사용자",
	           role: "NONE",
	           deptId: "UNKNOWN",
	           status: "UNKNOWN",
	           userId: "default_user"
	       };

	   },

	// 웹소켓 연결
	connect: function() {
	    try {
	        this.ws = new WebSocket(this.serverUrl);
	        
	        this.ws.onopen = function() {
	            console.log('🔗 웹소켓 연결 성공');
	            WebSocketService.isConnected = true;
	            
	            // 직원 정보 등록
	            WebSocketService.registerEmployee();
	            
	            // 연결 상태 표시
	            WebSocketService.updateConnectionStatus('연결됨', 'green');
	        };
	        
	        this.ws.onmessage = function(event) {
	            try {
	                var data = JSON.parse(event.data);
	                WebSocketService.handleMessage(data);
	            } catch (error) {
	                console.error('메시지 파싱 오류:', error);
	            }
	        };
	        
	        this.ws.onclose = function() {
	            console.log('🔌 웹소켓 연결 종료');
	            WebSocketService.isConnected = false;
	            WebSocketService.updateConnectionStatus('연결 끊김', 'red');
	            
	            // 3초 후 재연결 시도
	            setTimeout(function() {
	                WebSocketService.connect();
	            }, 3000);
	        };
	        
	        this.ws.onerror = function(error) {
	            console.error('🚨 웹소켓 오류:', error);
	            WebSocketService.updateConnectionStatus('연결 오류', 'red');
	        };
	        
	    } catch (error) {
	        console.error('웹소켓 초기화 실패:', error);
	    }
	},
    
    // 직원 등록
    registerEmployee: function() {
        if (this.currentUser) {

			setTimeout(() => {
			    if (this.ws.readyState === WebSocket.OPEN) {
			        const registerData = {
			            type: 'register',
			            empNo: this.currentUser.empNo,
			            empName: this.currentUser.empName,
			            deptId: this.currentUser.deptId,
			            role: this.currentUser.role,
			            status: this.currentUser.status
			        };
					
            this.ws.send(JSON.stringify(registerData));
			console.log('👤 직원 등록 요청 (지연 후 실행):', this.currentUser.empName + '(' + this.currentUser.empNo + ')');
		} else {
			   console.error("등록 시도 시, 웹소켓이 OPEN 상태가 아닙니다. 현재 상태:", this.ws.readyState);
		}
			}, 100);
        }
    },
    
		
		handleMessage: function(data) {
		    // 메시지 수신을 확인
		    console.log("[websocket.js] 메시지 수신!", data);

		    switch (data.type) {
				// 자동/수동 배정 알림
			       case 'auto_assigned_task':
			       case 'manual_assigned_task':
			           this.showMessage(data.message, 'info');
			           this.addToNotificationList(data);
			           break;
			           
			       // 결재 관련 알림
			       case 'approval_request':
			       case 'approval_result':
			           this.showMessage(data.message, 'info');
			           this.addToNotificationList(data);
			           break;
			           
			       // 고객/보완 관련 알림
			       case 'customer_notification':
			       case 'supplement_complete':
			           this.showMessage(data.message, 'info');
			           this.addToNotificationList(data);
			           break;
					
						
		            this.showMessage(data.message, 'info');
		            
		            this.addToNotificationList(data);
		            break;
		            

		        case 'register_success':
		            console.log('✅ 직원 등록 완료:', data.message);
		            this.updateConnectionStatus('등록 완료', 'green');
		            break;
		        
				case 'batch_assign_notification':
		            this.showBatchAssignNotification(data);
		            break;
						            
		        case 'dept_notification':
		            this.showDeptNotification(data);
		            break;
		            
		        case 'server_shutdown':
		            this.showMessage('서버가 종료됩니다. 잠시 후 다시 연결됩니다.', 'warning');
		            break;
		            
		        case 'pong':
		            // 하트비트 응답
		            break;
		            
		        default:
		            console.log('알 수 없는 메시지 타입:', data.type);
		    }
		},

		// 알림 타입별 제목 반환
		getNotificationTitle: function(type) {
		    const titles = {
		        'auto_assigned_task': '자동 배정',
		        'manual_assigned_task': '수동 배정', 
		        'approval_request': '결재 요청',
		        'approval_result': '결재 결과',
		        'customer_notification': '고객 알림',
		        'supplement_complete': '보완 완료'
		    };
		    return titles[type] || '알림';
		},
	

		
		addToNotificationList: function(data) {
		    try {
		        const notificationItem = {
		            noti_id: 'NOTI_' + Date.now(),
		            emp_no: this.currentUser.empNo,
		            noti_type: data.type,
		            noti_content: data.message,
		            is_read: 'N',
		            created_date: new Date().toLocaleString()
		        };

		        const event = new CustomEvent('notification_received', { detail: notificationItem });
		        document.dispatchEvent(event);
		        
		        console.log("[websocket.js] 'notification_received' 이벤트를 성공적으로 발생시켰습니다.");

		    } catch (error) {
		        console.error('[websocket.js] 알림 이벤트 생성 실패:', error);
		    }
		},
		

	    showNotificationPopup: function(data) {
	        let title = "";
	        let message = "";

	        if (data.type === 'auto_assigned_task') {
	            title = "자동 배정 알림";
	            message = '새로운 실손보험 심사 작업이 배정되었습니다!\n\n' +
	                      '청구번호: ' + data.taskData.claimNo;
	        } else if (data.type === 'manual_assigned_task') {
	            title = "수동 배정 알림";
	            message = '관리자가 새로운 심사 작업을 배정했습니다!\n\n' +
	                      '내용: ' + data.taskData.claimContent;
	        }
	        
	        if (typeof $p !== "undefined" && $p.alert) {
	            $p.alert(message, { title: title });
	        } else {
	            alert(message);
	        }
	        
	        this.showBrowserNotification(title, message);
	        this.playNotificationSound();
	    },

	    // 작업 목록 새로고침 함수
	    refreshTaskList: function() {
	        try {
	            const notiList = $p.getComponentById("notification_list");
	            if (notiList && typeof notiList.scwin.refreshNotifications === "function") {
	                notiList.scwin.refreshNotifications();
	            }
	        } catch (error) {

	        }
	    },
	
    // 자동 배정 작업 알림 표시
    showAutoAssignedTaskNotification: function(data) {
        var message = 
            '자동 배정으로 새로운 실손보험 심사 작업이 배정되었습니다!\n\n' +
            '청구번호: ' + data.taskData.claimNo + '\n' +
            '환자명: ' + data.taskData.patientName + '\n' +
            '병원명: ' + data.taskData.hospitalName + '\n' +
            '청구금액: ' + data.taskData.receiptAmount + '원\n' +
            '청구유형: ' + data.taskData.claimType + '\n' +
            '매칭키워드: ' + data.taskData.keyword + '\n' +
            '우선순위: ' + data.taskData.priority + '\n' +
            '배정시간: ' + data.taskData.assignTime;
        
        if (typeof $p !== 'undefined') {
            $p.confirm(message, function(result) {
                if (result) {
                    // 확인 클릭시 해당 작업 페이지로 이동
                    WebSocketService.openClaimDetailPage(data.taskData.claimNo);
                }
            }, {
                title: '자동 배정 알림',
                titleIcon: 'information'
            });
        } else {
            // 일반 브라우저 환경
            if (confirm(message + '\n\n작업 상세를 확인하시겠습니까?')) {
                this.openClaimDetailPage(data.taskData.claimNo);
            }
        }
        
        // 브라우저 알림
        this.showBrowserNotification('자동 배정', data.taskData.patientName + ' 환자 - ' + data.taskData.hospitalName);
        
        this.playNotificationSound();
        
        // 작업 목록 새로고침
        this.refreshTaskList();
    },
    
    // 수동 배정 작업 알림 표시
    showManualAssignedTaskNotification: function(data) {
        var message = 
            '관리자가 새로운 심사 작업을 배정했습니다!\n\n' +
            '청구번호: ' + data.taskData.claimNo + '\n' +
            '환자명: ' + data.taskData.patientName + '\n' +
            '병원명: ' + data.taskData.hospitalName + '\n' +
            '청구금액: ' + data.taskData.receiptAmount + '원\n' +
            '우선순위: ' + data.taskData.priority + '\n' +
            '배정자: ' + data.taskData.assignedBy + '\n' +
            '배정시간: ' + data.taskData.assignTime;
        
        if (typeof $p !== 'undefined') {
            $p.confirm(message, function(result) {
                if (result) {
                    WebSocketService.openClaimDetailPage(data.taskData.claimNo);
                }
            }, {
                title: '수동 배정 알림',
                titleIcon: 'information'
            });
        }
        
        this.showBrowserNotification('수동 배정', data.taskData.patientName + ' 환자');
        this.playNotificationSound();
        this.refreshTaskList();
    },
    
    // 일괄 배정 완료 알림
    showBatchAssignNotification: function(data) {
        var message = 
            '일괄 자동 배정이 완료되었습니다!\n\n' +
            '총 처리건수: ' + data.batchData.totalProcessed + '건\n' +
            '성공: ' + data.batchData.successCount + '건\n' +
            '실패: ' + data.batchData.failCount + '건\n' +
            '처리시간: ' + data.batchData.processTime;
        
        this.showMessage(message, 'info');
        this.refreshTaskList();
    },
    
    // 부서 알림
    showDeptNotification: function(data) {
        var message = 
            '🏢 ' + data.deptData.deptName + ' 부서 알림\n\n' +
            '새로운 업무가 ' + data.deptData.assignCount + '건 배정되었습니다.\n' +
            '시간: ' + data.deptData.notificationTime;
        
        this.showMessage(message, 'info');
    },
    
    // 청구 상세 페이지 열기
    openClaimDetailPage: function(claimNo) {
        try {
            if (typeof $p !== 'undefined') {
                $p.openPopup('/ui/claim/claim_detail.xml', {
                    width: 1000,
                    height: 800,
                    claimNo: claimNo
                });
            } else {
                console.log('청구 상세 보기:', claimNo);
                // window.open('/claim/detail?claimNo=' + claimNo, '_blank');
            }
        } catch (error) {
            console.error('청구 상세 페이지 열기 실패:', error);
        }
    },
    
    // 작업 목록 새로고침
    refreshTaskList: function() {
        try {

			if (typeof scwin !== 'undefined' && typeof scwin.refreshNotifications === 'function') {
			    console.log('알림 목록 새로고침 함수(scwin.refreshNotifications)를 호출합니다.');
			    scwin.refreshNotifications();
			} else {
			    console.warn('알림 목록 새로고침 함수(scwin.refreshNotifications)를 찾을 수 없습니다.');
			}
            
        } catch (error) {
            console.error('작업 목록 새로고침 실패:', error);
        }
    },
    
    // 브라우저 알림
    showBrowserNotification: function(title, body) {
        if ('Notification' in window && Notification.permission === 'granted') {
            new Notification(title, {
                body: body,
                icon: '/images/notification-icon.png'
            });
        }
    },
    
    playNotificationSound: function() {
        try {

            var audio = new Audio('/sounds/notification.mp3');
            audio.volume = 0.5;
            audio.play().catch(function(e) {
                console.log('알림 소리 재생 실패 (정상)');
            });
        } catch (error) {
        }
    },
    
	
	// 메시지 표시
	   showMessage: function(message, type) {
	           console.warn("웹스퀘어 전용 alert 함수를 찾을 수 없어 브라우저 기본 alert()를 사용합니다.");
	       
	   },
    
    // 연결 상태 업데이트
    updateConnectionStatus: function(status, color) {
        try {
            if (typeof tbx_wsStatus !== 'undefined') {
                tbx_wsStatus.setValue('웹소켓: ' + status);
                tbx_wsStatus.setStyle('color', color);
            }
            
            if (typeof lbl_connectionStatus !== 'undefined') {
                lbl_connectionStatus.setValue(status);
                lbl_connectionStatus.setStyle('color', color);
            }
            
        } catch (error) {
        }
        
        console.log('웹소켓 상태:', status);
    },
    
    // 수동 작업 배정
    assignTaskManually: function(taskData) {
        if (this.isConnected) {
            var assignData = {
                type: 'manual_assign_task',
                targetEmpNo: taskData.targetEmpNo,    
                claimNo: taskData.claimNo,
				
				claimContent: taskData.claimContent,
				claimType: taskData.claimType,
				
                patientName: taskData.patientName,
                hospitalName: taskData.hospitalName,
                receiptAmount: taskData.receiptAmount,
                priority: taskData.priority || 'NORMAL',
                assignedBy: this.currentUser.empName
            };
            
            this.ws.send(JSON.stringify(assignData));
            console.log('수동 작업 배정 요청:', taskData);
        } else {
            this.showMessage('웹소켓이 연결되지 않았습니다.', 'error');
        }
    },
    
    // 접속 중인 직원 목록 요청
    getOnlineEmployees: function() {
        if (this.isConnected) {
            this.ws.send(JSON.stringify({ type: 'get_online_employees' }));
        }
    },
    
    // 부서별 접속 현황 요청
    getDeptStatus: function() {
        if (this.isConnected) {
            this.ws.send(JSON.stringify({ type: 'get_dept_status' }));
        }
    },
    
    // 하트비트 설정
    setupHeartbeat: function() {
        var self = this;
        this.heartbeatInterval = setInterval(function() {
            if (self.isConnected) {
                self.ws.send(JSON.stringify({ type: 'ping' }));
            }
        }, 30000); // 30초마다
    },
    
    // 연결 종료
    disconnect: function() {
        if (this.heartbeatInterval) {
            clearInterval(this.heartbeatInterval);
        }
        
        if (this.ws) {
            this.ws.close();
        }
        
        this.isConnected = false;
        console.log('웹소켓 연결 종료');
    }
};

// 브라우저 알림 권한 요청
if ('Notification' in window && Notification.permission === 'default') {
    Notification.requestPermission();
}

// 웹스퀘어 전역 함수로 노출
if (typeof window !== 'undefined') {
    window.WebSocketService = WebSocketService;
}