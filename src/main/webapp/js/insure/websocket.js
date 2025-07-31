/**
 * 실손보험 배정 시스템 웹소켓 알림 서비스
 * 웹스퀘어 프로젝트용 (ProworksUserHeader 연동)
 */
var WebSocketService = {
    ws: null,
    isConnected: false,
    serverUrl: 'ws://localhost:8081',
    currentUser: null,
    heartbeatInterval: null,
    
	// 초기화 (웹스퀘어 세션 정보 사용)
	init: function() {
	    this.loadUserInfoFromSession();
	    this.connect();
	    this.setupHeartbeat();
	},

	// 웹스퀘어 세션에서 사용자 정보 로드
	/*loadUserInfoFromSession: function() {
	    try { // <-- 여기에 try 블록이 시작됩니다.
	        var userId = "";    // 고객용 userId (예비)
	        var empNo = "";     // 직원번호 (주요)
	        var empName = "";   // 직원명
	        var userRole = "";  // 사용자 역할 (EMPLOYEE, CUSTOMER 등)
	        var deptId = "";    // 부서 ID
	        var empStatus = ""; // 직원 상태

	        // $p.getSession() 또는 com 객체를 통해 세션 정보 가져오기
	        // 백엔드에서 설정한 정확한 세션 키를 사용합니다.
	        if (typeof $p !== 'undefined' && $p.getSession) {
	            empNo = $p.getSession("empNo"); // ★ 이 부분!
	            empName = $p.getSession("empName"); // ★ 이 부분!
	            deptId = $p.getSession("deptId"); // ★ 이 부분!
	            userRole = $p.getSession("role"); // ★ 이 부분! (여기서는 'role'을 userRole로 사용)
	            empStatus = $p.getSession("empStatus"); // ★ 이 부분!

	            // 고객 ID도 필요하다면 (나중에 고객 알림 시 활용)
	            userId = $p.getSession("userId");
	        }

	        // 또는 com 객체 사용 (둘 중 하나만 사용해도 되지만, 안전을 위해 남겨둘 수 있음)
	        if (!empNo && typeof com !== 'undefined') {
	            empNo = com.getSession("empNo");
	            empName = com.getSession("empName");
	            deptId = com.getSession("deptId");
	            userRole = com.getSession("role");
	            empStatus = com.getSession("empStatus");

	            userId = com.getSession("userId");
	        }

	        // 기본값 설정 (개발/테스트용 - 실제 운영 시에는 이 부분이 동작하지 않도록 해야 합니다.)
	        if (!empNo) { // 직원 번호가 없으면 기본값 사용
	            empNo = "EMP001"; // 기본 직원번호
	            empName = "테스트직원";
	            userRole = "EMPLOYEE"; // 기본 역할
	            deptId = "DEPT001";    // 기본 부서
	            empStatus = "ACTIVE";  // 기본 상태
	            console.warn("세션 정보 (직원번호)가 없어 기본값을 사용합니다.");
	        }

	        this.currentUser = {
	            empNo: empNo,
	            empName: empName,
	            role: userRole, // 웹소켓 서버에서 사용하는 role 필드에 매핑
	            deptId: deptId,
	            status: empStatus,
	            userId: userId // 고객용 userId도 함께 저장 (고객 알림 시 활용)
	        };

	        console.log("로드된 사용자 정보:", this.currentUser);

	    } catch (error) { // <-- 여기에 catch 블록이 있습니다.
	        console.error("세션 정보 로드 실패:", error);
	        // 에러 발생 시에도 기본값으로 설정하여 연결은 시도하도록
	        this.currentUser = {
	            empNo: "EMP001",
	            empName: "테스트직원",
	            role: "EMPLOYEE",
	            deptId: "DEPT001",
	            status: "ACTIVE",
	            userId: "CUST001" // 고객 ID 기본값
	        };
	    }
	}, */
	
	// 웹스퀘어 세션에서 사용자 정보 로드 (수정된 부분)
	   loadUserInfoFromSession: function() { // 이제 이것은 WebSocketService 객체의 메서드입니다.
	       let userKey = "default_user"; // 기본값 설정
	       let userInfo = { key: userKey, type: "NONE" }; // 반환할 최종 객체

	       // 1. 웹스퀘어 세션에서 empNo 가져오기 (우선 순위 높임)
	       // $p.getSession() 또는 WebSquare.session.get() 사용 가능
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
	           this.currentUser = { empNo: userKey, role: "EMPLOYEE" }; // currentUser도 설정
	           return; // 찾았으면 바로 종료
	       }

	       // 2. localStorage에서 employee 정보 가져오기
	       try {
	           const employeeData = localStorage.getItem("employee");
	           if (employeeData) {
	               const employee = JSON.parse(employeeData);
	               if (employee && employee.empNo) {
	                   userKey = employee.empNo;
	                   userInfo = { key: userKey, type: "EMPLOYEE" };
	                   console.log("로컬스토리지(employee)에서 로드된 직원번호:", userKey);
	                   this.currentUser = employee; // currentUser 객체 전체를 저장
	                   return; // 찾았으면 바로 종료
	               }
	           }
	       } catch (error) {
	           console.error("로컬스토리지 'employee' 데이터 파싱 실패:", error);
	       }

	       // 3. localStorage에서 simple 정보 가져오기
	       try {
	           const simpleData = localStorage.getItem("simple");
	           if (simpleData) {
	               const simple = JSON.parse(simpleData);
	               if (simple && simple.userId) { // simple 로그인 시 userId 사용 가정
	                   userKey = simple.userId;
	                   userInfo = { key: userKey, type: "SIMPLE" };
	                   console.log("로컬스토리지(simple)에서 로드된 사용자 ID:", userKey);
	                   this.currentUser = { empNo: userKey, role: "SIMPLE" }; // currentUser 설정 (simple type)
	                   return; // 찾았으면 바로 종료
	               }
	           }
	       } catch (error) {
	           console.error("로컬스토리지 'simple' 데이터 파싱 실패:", error);
	       }

	       // 4. localStorage에서 user 정보 가져오기
	       try {
	           const userData = localStorage.getItem("user");
	           if (userData) {
	               const user = JSON.parse(userData);
	               if (user && user.userId) { // user 로그인 시 userId 사용 가정
	                   userKey = user.userId;
	                   userInfo = { key: userKey, type: "USER" };
	                   console.log("로컬스토리지(user)에서 로드된 사용자 ID:", userKey);
	                   this.currentUser = { empNo: userKey, role: "USER" }; // currentUser 설정 (user type)
	                   return; // 찾았으면 바로 종료
	               }
	           }
	       } catch (error) {
	           console.error("로컬스토리지 'user' 데이터 파싱 실패:", error);
	       }

	       // 모든 방법을 시도했지만 사용자 정보를 찾지 못한 경우
	       console.warn("세션 정보 (직원번호/사용자 ID)가 없어 기본값을 사용합니다.");
	       this.currentUser = { // 기본 사용자 정보 설정
	           empNo: "default_user",
	           empName: "기본사용자",
	           role: "NONE",
	           deptId: "UNKNOWN",
	           status: "UNKNOWN",
	           userId: "default_user"
	       };
	       // 함수 끝에서는 아무것도 return 하지 않아도 됨. this.currentUser에 저장했기 때문.
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

			// ❗️ setTimeout으로 감싸서 실행을 잠시 지연시킵니다.
			setTimeout(() => {
			    // 웹소켓의 상태가 'OPEN'일 때만 데이터를 전송하도록 명확하게 체크합니다.
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
			}, 100); // 100ms(0.1초) 정도의 지연 시간을 줍니다.
        }
    },
    
		// 메시지 처리
		/*handleMessage: function(data) {
			
			console.log("[관리자 브라우저] 메시지 수신!", data);
			
		    switch (data.type) {
		        // 🔔 실시간 알림이 필요한 케이스들
		        case 'auto_assigned_task':
					case 'manual_assigned_task':
					    // ▼ [수정] 'showNotification'을 'showMessage'으로 변경합니다.
					    // 'info' 타입은 정보성 파란색 아이콘 팝업을 띄웁니다.
					    this.showMessage(data.message, 'info'); 

					    // 알림 목록(그리드)에 데이터를 추가하는 코드는 그대로 둡니다.
					    this.addToNotificationList(data); 
					    break;
		        case 'approval_request':
		        case 'approval_result':
		        case 'customer_notification':
		        case 'supplement_complete':
		            // 알림 팝업 표시
					this.showMessage(data.message, 'info');

		            
		            // header.xml 알림 목록에 추가
		            this.addToNotificationList(data);
		            break;
		            
		        // 📋 기존 시스템 메시지들 (알림 목록에 추가 안 함)
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
		            // 하트비트 응답 (로그 생략)
		            break;
		            
		        default:
		            console.log('알 수 없는 메시지 타입:', data.type);
		    }
		},*/
		
		handleMessage: function(data) {
		    // 메시지 수신을 확인하는 로그
		    console.log("📩 [websocket.js] 메시지 수신!", data);

		    switch (data.type) {
		        // 실시간 알림이 필요한 모든 케이스를 여기에 포함합니다.
				// 🔔 자동/수동 배정 알림
			       case 'auto_assigned_task':
			       case 'manual_assigned_task':
			           this.showMessage(data.message, 'info');
			           this.addToNotificationList(data);
			           break;
			           
			       // 🔔 결재 관련 알림
			       case 'approval_request':
			       case 'approval_result':
			           this.showMessage(data.message, 'info');
			           this.addToNotificationList(data);
			           break;
			           
			       // 🔔 고객/보완 관련 알림
			       case 'customer_notification':
			       case 'supplement_complete':
			           this.showMessage(data.message, 'info');
			           this.addToNotificationList(data);
			           break;
					
						
		            // 1. 팝업 알림을 표시합니다.
		            this.showMessage(data.message, 'info');
		            
		            // 2. 그리드에 데이터를 추가하기 위해 이벤트를 발생시킵니다.
		            this.addToNotificationList(data);
		            break;
		            
		        // --- 이하 시스템 메시지 처리 (수정 없음) ---
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
		            // 하트비트 응답 (로그 생략)
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
		    return titles[type] || '🔔 알림';
		},
	
		/*addToNotificationList: function(data) {
		    // --- ▼ 디버깅 로그 추가 ▼ ---
		    console.log("[디버깅] addToNotificationList 함수가 호출되었습니다.");
		    console.log("[디버깅] scwin 객체의 타입:", typeof scwin);

		    if (typeof scwin !== 'undefined') {
		        console.log("[디버깅] scwin.addNotificationToList 함수의 타입:", typeof scwin.addNotificationToList);
		    }
		    // --- ▲ 디버깅 로그 추가 ▲ ---
		    try {
		        // header.xml의 함수 호출해서 알림 목록에 추가
		        if (typeof scwin !== 'undefined' && typeof scwin.addNotificationToList === 'function') {
		           
					 const notificationItem = {
						// [추가] 간단한 고유 ID 생성
						noti_id: 'NOTI_' + Date.now(),

						// [추가] 현재 로그인한 사용자 ID
						emp_no: this.currentUser.empNo,
						
		                noti_type: data.type,           
		                noti_content: data.message,     
		                is_read: 'N',                   
		                created_date: new Date().toLocaleString() 
		            };
		            
		            scwin.addNotificationToList(notificationItem); // ← header.xml 함수 호출
		        }
		        
		        // 알림 배지 업데이트
		        if (typeof scwin !== 'undefined' && typeof scwin.updateNotificationBadge === 'function') {
		            scwin.updateNotificationBadge(); // ← header.xml 함수 호출
		        }
		        
		    } catch (error) {
		        console.error('알림 목록 추가 실패:', error);
		    }
		},*/
		
		addToNotificationList: function(data) {
		    try {
		        // header.xml로 보낼 데이터 객체를 생성합니다.
		        const notificationItem = {
		            noti_id: 'NOTI_' + Date.now(),
		            emp_no: this.currentUser.empNo,
		            noti_type: data.type,
		            noti_content: data.message,
		            is_read: 'N',
		            created_date: new Date().toLocaleString()
		        };

		        // 'notification_received' 라는 이름으로 이벤트를 발생시킵니다.
		        const event = new CustomEvent('notification_received', { detail: notificationItem });
		        document.dispatchEvent(event);
		        
		        console.log("✅ [websocket.js] 'notification_received' 이벤트를 성공적으로 발생시켰습니다.");

		    } catch (error) {
		        console.error('❌ [websocket.js] 알림 이벤트 생성 실패:', error);
		    }
		},
		
	/**
	     * ❗️ [핵심] WebSquare 전용 팝업을 띄우는 함수
	     * 브라우저 기본 alert 대신 $p.alert을 사용합니다.
	     */
	    showNotificationPopup: function(data) {
	        let title = "";
	        let message = "";

	        if (data.type === 'auto_assigned_task') {
	            title = "🤖 자동 배정 알림";
	            message = '새로운 실손보험 심사 작업이 배정되었습니다!\n\n' +
	                      '청구번호: ' + data.taskData.claimNo;
	        } else if (data.type === 'manual_assigned_task') {
	            title = "👨‍💼 수동 배정 알림";
	            message = '관리자가 새로운 심사 작업을 배정했습니다!\n\n' +
	                      '내용: ' + data.taskData.claimContent;
	        }
	        
	        // $p.alert 또는 $p.confirm을 사용
	        if (typeof $p !== "undefined" && $p.alert) {
	            $p.alert(message, { title: title });
	        } else {
	            // WebSquare 전역 객체를 찾지 못할 경우를 대비
	            alert(message);
	        }
	        
	        // 브라우저 자체 알림 및 소리 재생은 그대로 유지
	        this.showBrowserNotification(title, message);
	        this.playNotificationSound();
	    },

	    // 작업 목록(notification_list.xml) 새로고침 함수
	    refreshTaskList: function() {
	        try {
	            // 알림 목록 컴포넌트의 스코프를 찾아 새로고침 함수를 호출
	            // 이 또한 알림 목록 화면의 ID를 알아야 합니다.
	            const notiList = $p.getComponentById("notification_list"); // 예시 ID
	            if (notiList && typeof notiList.scwin.refreshNotifications === "function") {
	                notiList.scwin.refreshNotifications();
	            }
	        } catch (error) {
	            // 알림 목록 화면이 현재 열려있지 않으면 오류가 날 수 있으나, 정상적인 상황임.
	        }
	    },
	
    // 자동 배정 작업 알림 표시
    showAutoAssignedTaskNotification: function(data) {
        var message = 
            '🤖 자동 배정으로 새로운 실손보험 심사 작업이 배정되었습니다!\n\n' +
            '청구번호: ' + data.taskData.claimNo + '\n' +
            '환자명: ' + data.taskData.patientName + '\n' +
            '병원명: ' + data.taskData.hospitalName + '\n' +
            '청구금액: ' + data.taskData.receiptAmount + '원\n' +
            '청구유형: ' + data.taskData.claimType + '\n' +
            '매칭키워드: ' + data.taskData.keyword + '\n' +
            '우선순위: ' + data.taskData.priority + '\n' +
            '배정시간: ' + data.taskData.assignTime;
        
        // 웹스퀘어 확인창
        if (typeof $p !== 'undefined') {
            $p.confirm(message, function(result) {
                if (result) {
                    // 확인 클릭시 해당 작업 페이지로 이동
                    WebSocketService.openClaimDetailPage(data.taskData.claimNo);
                }
            }, {
                title: '🤖 자동 배정 알림',
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
        
        // 소리 알림
        this.playNotificationSound();
        
        // 작업 목록 새로고침
        this.refreshTaskList();
    },
    
    // 수동 배정 작업 알림 표시
    showManualAssignedTaskNotification: function(data) {
        var message = 
            '👨‍💼 관리자가 새로운 심사 작업을 배정했습니다!\n\n' +
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
                title: '👨‍💼 수동 배정 알림',
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
            '📊 일괄 자동 배정이 완료되었습니다!\n\n' +
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
                // 웹스퀘어 팝업으로 청구 상세 열기
                $p.openPopup('/ui/claim/claim_detail.xml', {
                    width: 1000,
                    height: 800,
                    claimNo: claimNo
                });
            } else {
                // 일반 브라우저 환경
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
            // 현재 화면에 작업 목록 새로고침 함수가 있다면 호출
            /*if (typeof scwin !== 'undefined' && typeof scwin.refreshTaskList === 'function') {
                scwin.refreshTaskList();
            }
            
            // 또는 특정 그리드 새로고침
            if (typeof grd_taskList !== 'undefined' && typeof dma_taskList !== 'undefined') {
                // 그리드 데이터 새로고침 로직
                console.log('작업 목록 그리드 새로고침');
            }*/
			
			// 현재 화면에 알림 목록 컴포넌트(notification_list.xml)가 있다면
			// 해당 컴포넌트의 새로고침 함수를 호출합니다.
			// notification_list.xml의 scwin.refreshNotifications 함수를 호출하는 것이 좋습니다.
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
    
    // 알림 소리
    playNotificationSound: function() {
        try {
            // 실제 mp3 파일이 있다면 사용
            var audio = new Audio('/sounds/notification.mp3');
            audio.volume = 0.5;
            audio.play().catch(function(e) {
                // 파일이 없어도 오류 무시
                console.log('알림 소리 재생 실패 (정상)');
            });
        } catch (error) {
            // 소리 재생 실패해도 무시
        }
    },
    
    // 메시지 표시
    /*showMessage: function(message, type) {
        if (typeof $p !== 'undefined') {
            if (type === 'success') {
                $p.alert(message, { title: '✅ 성공', titleIcon: 'information' });
            } else if (type === 'error') {
                $p.alert(message, { title: '❌ 오류', titleIcon: 'warning' });
            } else if (type === 'info') {
                $p.alert(message, { title: 'ℹ️ 정보', titleIcon: 'information' });
            } else if (type === 'warning') {
                $p.alert(message, { title: '⚠️ 경고', titleIcon: 'warning' });
            }
        } else {
            alert(message);
        }
    },*/
	
	// 메시지 표시 (가장 범용적인 알림 함수)
	   showMessage: function(message, type) {
	           console.warn("웹스퀘어 전용 alert 함수를 찾을 수 없어 브라우저 기본 alert()를 사용합니다.");
	       
	   },
    
    // 연결 상태 업데이트
    updateConnectionStatus: function(status, color) {
        // 웹스퀘어 컴포넌트가 있다면 업데이트
        try {
            if (typeof tbx_wsStatus !== 'undefined') {
                tbx_wsStatus.setValue('웹소켓: ' + status);
                tbx_wsStatus.setStyle('color', color);
            }
            
            // 또는 다른 상태 표시 컴포넌트
            if (typeof lbl_connectionStatus !== 'undefined') {
                lbl_connectionStatus.setValue(status);
                lbl_connectionStatus.setStyle('color', color);
            }
            
        } catch (error) {
            // 컴포넌트가 없어도 무시
        }
        
        console.log('웹소켓 상태:', status);
    },
    
    // 수동 작업 배정 (관리자용)
    assignTaskManually: function(taskData) {
        if (this.isConnected) {
            var assignData = {
                type: 'manual_assign_task',
                targetEmpNo: taskData.targetEmpNo,    // emp_no 사용
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
            console.log('📤 수동 작업 배정 요청:', taskData);
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

// 웹스퀘어 전역 함수로 노출 (필요시)
if (typeof window !== 'undefined') {
    window.WebSocketService = WebSocketService;
}