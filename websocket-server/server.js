const WebSocket = require('ws');
const express = require('express');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

// 웹소켓 서버
const wss = new WebSocket.Server({ port: 8081 });
const connectedEmployees = new Map(); // empNo를 키로 사용

console.log('🚀 실손보험 배정 시스템 웹소켓 서버 시작 (포트: 8081)');
console.log('📋 연동 가능한 VO: AssignRuleVo, EmployeeVo, ClaimVo, UserVo');

wss.on('connection', function connection(ws) {
    console.log('👤 새 연결 수립');
	

	// --- ▼ 디버깅용 로그 1번 추가 ▼ ---
	console.log('✅ "message" 이벤트 핸들러를 이 연결에 추가합니다.');

	ws.on('message', function incoming(message) {
	    
	    // --- ▼ 디버깅용 로그 2번 추가 ▼ ---
	    // Buffer 형태로 올 수 있으므로 toString()으로 변환하여 확인합니다.
	    console.log('📩 메시지 수신 완료! 내용:', message.toString());

        try {
            const data = JSON.parse(message);
            
            // 직원 등록 (EmployeeVo 기반)
            if (data.type === 'register') {
                connectedEmployees.set(data.empNo, {
                    ws: ws,
                    empNo: data.empNo,           // EmployeeVo.empNo
                    empName: data.empName,       // EmployeeVo.empName  
                    deptId: data.deptId,         // EmployeeVo.deptId
                    role: data.role,             // EmployeeVo.role (실무자, 관리자)
                    status: data.status,         // EmployeeVo.status (재직중, 퇴사)
                    userRole: data.userRole,     // ProworksUserHeader.userRole (USER, EMPLOYEE)
                    connectTime: new Date(),
                    lastHeartbeat: new Date()
                });
                
                console.log(`✅ 직원 등록 완료:`);
                console.log(`   - 직원번호: ${data.empNo}`);
                console.log(`   - 직원명: ${data.empName}`);
                console.log(`   - 부서ID: ${data.deptId}`);
                console.log(`   - 직급: ${data.role}`);
                console.log(`   - 상태: ${data.status}`);
                console.log(`   - 사용자유형: ${data.userRole}`);
                
               /* ws.send(JSON.stringify({
                    type: 'register_success',
                    message: `${data.empName}님이 실손보험 알림 서비스에 연결되었습니다.`,
                    empInfo: {
                        empNo: data.empNo,
                        empName: data.empName,
                        deptId: data.deptId,
                        role: data.role,
                        status: data.status
                    }
                }));*/
            }
            
            // 자동 배정 완료 알림 (AssignRuleServiceImpl.assignEmployeeToClaim 결과)
            if (data.type === 'auto_assign_complete') {
                const targetEmployee = connectedEmployees.get(data.assignedEmpNo);
                
                if (targetEmployee && targetEmployee.ws.readyState === WebSocket.OPEN) {
                    const notification = {
                        type: 'auto_assigned_task',
                        taskId: data.claimNo,
                        message: '🤖 자동 배정으로 새로운 실손보험 심사 작업이 배정되었습니다!',
                        taskData: {
                            // ClaimVo 필드 기반
                            claimNo: data.claimNo,               // ClaimVo.claim_no
                            claimType: data.claimType,           // ClaimVo.claim_type
                            claimContent: data.claimContent,     // ClaimVo.claim_content
                            receiptDate: data.receiptDate,       // ClaimVo.receipt_date
                            diseaseCode: data.diseaseCode,       // ClaimVo.disease_code
                            dateOfAccident: data.dateOfAccident, // ClaimVo.date_of_accident
                            
                            // 배정 정보
                            assignedEmpNo: data.assignedEmpNo,   // 배정된 직원번호
                            assignedEmpName: targetEmployee.empName,
                            assignedDeptId: targetEmployee.deptId,
                            assignedRole: targetEmployee.role,
                            
                            // 배정규칙 정보 (AssignRuleVo 기반)
                            ruleId: data.ruleId,                 // AssignRuleVo.rule_id
                            keyword: data.keyword,               // AssignRuleVo.keyword
                            assignDept: data.assignDept,         // AssignRuleVo.dept
                            
                            assignTime: new Date().toLocaleString(),
                            assignType: 'AUTO',
                            priority: data.priority || 'NORMAL'
                        }
                    };
                    
                    targetEmployee.ws.send(JSON.stringify(notification));
                    
                    console.log(`🤖 자동 배정 알림 전송:`);
                    console.log(`   - 청구번호: ${data.claimNo}`);
                    console.log(`   - 청구유형: ${data.claimType}`);
                    console.log(`   - 담당자: ${targetEmployee.empName}(${data.assignedEmpNo})`);
                    console.log(`   - 배정부서: ${data.assignDept}`);
                    console.log(`   - 매칭키워드: ${data.keyword}`);
                    
                    // 배정 성공 응답
                    ws.send(JSON.stringify({
                        type: 'assign_success',
                        message: `자동 배정 완료 - ${targetEmployee.empName}(${data.assignedEmpNo})에게 청구 ${data.claimNo} 배정됨`
                    }));
                } else {
                    console.warn(`⚠️  배정된 직원이 접속하지 않음: ${data.assignedEmpNo}`);
                    ws.send(JSON.stringify({
                        type: 'assign_failed',
                        message: `배정된 직원(${data.assignedEmpNo})이 현재 접속하지 않았습니다.`
                    }));
                }
            }
            
            // 수동 작업 배정
            if (data.type === 'manual_assign_task') {
                const targetEmployee = connectedEmployees.get(data.targetEmpNo);
                
                if (targetEmployee && targetEmployee.ws.readyState === WebSocket.OPEN) {
                    targetEmployee.ws.send(JSON.stringify({
                        type: 'manual_assigned_task',
                        taskId: data.claimNo,
                        message: '👨‍💼 관리자가 새로운 심사 작업을 배정했습니다.',
                        taskData: {
                            claimNo: data.claimNo,
                            claimType: data.claimType,
                            claimContent: data.claimContent,
                            receiptDate: data.receiptDate,
                            assignedEmpNo: data.targetEmpNo,
                            assignedEmpName: targetEmployee.empName,
                            assignTime: new Date().toLocaleString(),
                            assignType: 'MANUAL',
                            assignedBy: data.assignedBy,
                            priority: data.priority || 'NORMAL'
                        }
                    }));
                    
                    console.log(`👨‍💼 수동 배정: ${data.claimNo} → ${targetEmployee.empName}`);
                    
                    ws.send(JSON.stringify({
                        type: 'assign_success',
                        message: `${targetEmployee.empName}에게 작업이 배정되었습니다.`
                    }));
                } else {
                    ws.send(JSON.stringify({
                        type: 'assign_failed',
                        message: '해당 직원이 접속하지 않았습니다.'
                    }));
                }
            }
            
            // 일괄 배정 완료 알림 (AssignRuleServiceImpl.assignAllUnassignedClaims 결과)
            if (data.type === 'batch_assign_complete') {
                const batchNotification = {
                    type: 'batch_assign_notification',
                    message: `📊 일괄 자동 배정이 완료되었습니다!`,
                    batchData: {
                        totalProcessed: data.totalProcessed,
                        successCount: data.successCount,
                        failCount: data.failCount,
                        processTime: new Date().toLocaleString(),
                        processedClaims: data.processedClaims || [] // 처리된 청구 목록
                    }
                };
                
                // 관리자급 직원들에게만 알림 (role이 '관리자'인 경우)
                let notifiedCount = 0;
                connectedEmployees.forEach((employee) => {
                    if (employee.ws.readyState === WebSocket.OPEN && 
                        (employee.role === '관리자' || employee.userRole === 'EMPLOYEE')) {
                        employee.ws.send(JSON.stringify(batchNotification));
                        notifiedCount++;
                    }
                });
                
                console.log(`📊 일괄 배정 완료 알림:`);
                console.log(`   - 총 처리: ${data.totalProcessed}건`);
                console.log(`   - 성공: ${data.successCount}건`);
                console.log(`   - 실패: ${data.failCount}건`);
                console.log(`   - 알림 대상: ${notifiedCount}명`);
            }
            
            // 부서별 배정 알림
            if (data.type === 'dept_assign_notification') {
                let notifiedCount = 0;
                connectedEmployees.forEach((employee) => {
                    if (employee.deptId === data.deptId && employee.ws.readyState === WebSocket.OPEN) {
                        employee.ws.send(JSON.stringify({
                            type: 'dept_notification',
                            message: `🏢 ${data.deptName} 부서에 새로운 업무가 배정되었습니다.`,
                            deptData: {
                                deptId: data.deptId,
                                deptName: data.deptName,
                                assignCount: data.assignCount,
                                notificationTime: new Date().toLocaleString()
                            }
                        }));
                        notifiedCount++;
                    }
                });
                
                console.log(`🏢 부서 알림: ${data.deptName}(${data.deptId}) - ${notifiedCount}명에게 전송`);
            }
            
            // 접속 중인 직원 목록 요청
            if (data.type === 'get_online_employees') {
                const employees = [];
                connectedEmployees.forEach((emp, empNo) => {
                    if (emp.ws.readyState === WebSocket.OPEN) {
                        employees.push({
                            empNo: empNo,
                            empName: emp.empName,
                            deptId: emp.deptId,
                            role: emp.role,
                            status: emp.status,
                            userRole: emp.userRole,
                            connectTime: emp.connectTime,
                            lastHeartbeat: emp.lastHeartbeat
                        });
                    }
                });
                
                ws.send(JSON.stringify({
                    type: 'online_employees_list',
                    employees: employees,
                    totalCount: employees.length,
                    responseTime: new Date().toLocaleString()
                }));
            }
            
            // 부서별 접속 현황 요청
            if (data.type === 'get_dept_status') {
                const deptStatus = new Map();
                
                connectedEmployees.forEach((emp) => {
                    if (emp.ws.readyState === WebSocket.OPEN) {
                        const deptId = emp.deptId || '미분류';
                        if (!deptStatus.has(deptId)) {
                            deptStatus.set(deptId, {
                                deptId: deptId,
                                onlineCount: 0,
                                employees: [],
                                roles: new Set()
                            });
                        }
                        
                        const dept = deptStatus.get(deptId);
                        dept.onlineCount++;
                        dept.employees.push({
                            empNo: emp.empNo,
                            empName: emp.empName,
                            role: emp.role,
                            status: emp.status
                        });
                        dept.roles.add(emp.role);
                    }
                });
                
                // Set을 Array로 변환
                const departments = Array.from(deptStatus.values()).map(dept => ({
                    ...dept,
                    roles: Array.from(dept.roles)
                }));
                
                ws.send(JSON.stringify({
                    type: 'dept_status_list',
                    departments: departments,
                    totalDepartments: departments.length
                }));
            }
            
            // 하트비트 (연결 유지)
            if (data.type === 'ping') {
                // 하트비트 시간 업데이트
                connectedEmployees.forEach((emp) => {
                    if (emp.ws === ws) {
                        emp.lastHeartbeat = new Date();
                    }
                });
                ws.send(JSON.stringify({ type: 'pong', serverTime: new Date().toISOString() }));
            }
            
        } catch (error) {
            console.error('메시지 처리 오류:', error);
            ws.send(JSON.stringify({
                type: 'error',
                message: '메시지 처리 중 오류가 발생했습니다: ' + error.message
            }));
        }
    });
    
    ws.on('close', function() {
        connectedEmployees.forEach((emp, empNo) => {
            if (emp.ws === ws) {
                console.log(`👋 연결 종료: ${emp.empName}(${empNo})`);
                connectedEmployees.delete(empNo);
            }
        });
    });
    
    ws.on('error', function(error) {
        console.error('웹소켓 연결 오류:', error);
    });
});

// ==================== HTTP API 엔드포인트 ====================

// 자동 배정 완료 알림 API (AssignRuleServiceImpl에서 호출)
app.post('/api/notify-auto-assign', (req, res) => {
    try {
        const { 
            claimNo, 
            claimType, 
            claimContent,
            receiptDate,
            diseaseCode,
            dateOfAccident,
            assignedEmpNo, 
            ruleId,
            keyword, 
            assignDept,
            priority 
        } = req.body;
        
        console.log(`📨 자동 배정 알림 API 호출:`);
        console.log(`   - 청구번호: ${claimNo}`);
        console.log(`   - 청구유형: ${claimType}`);
        console.log(`   - 배정직원: ${assignedEmpNo}`);
        console.log(`   - 배정부서: ${assignDept}`);
        
        const targetEmployee = connectedEmployees.get(assignedEmpNo);
        
        if (targetEmployee && targetEmployee.ws.readyState === WebSocket.OPEN) {
            const notification = {
                type: 'auto_assigned_task',
                taskId: claimNo,
                message: '🤖 자동 배정으로 새로운 실손보험 심사 작업이 배정되었습니다!',
                taskData: {
                    claimNo,
                    claimType,
                    claimContent,
                    receiptDate,
                    diseaseCode,
                    dateOfAccident,
                    assignedEmpNo,
                    assignedEmpName: targetEmployee.empName,
                    assignedDeptId: targetEmployee.deptId,
                    assignedRole: targetEmployee.role,
                    ruleId,
                    keyword,
                    assignDept,
                    assignTime: new Date().toLocaleString(),
                    assignType: 'AUTO',
                    priority: priority || 'NORMAL'
                }
            };
            
            targetEmployee.ws.send(JSON.stringify(notification));
            
            res.json({ 
                success: true, 
                message: '자동 배정 알림 전송 완료',
                targetEmployee: {
                    empNo: assignedEmpNo,
                    empName: targetEmployee.empName,
                    deptId: targetEmployee.deptId,
                    role: targetEmployee.role
                }
            });
        } else {
            res.json({ 
                success: false, 
                message: '배정된 직원이 접속하지 않음',
                assignedEmpNo: assignedEmpNo
            });
        }
    } catch (error) {
        console.error('자동 배정 알림 API 오류:', error);
        res.status(500).json({ 
            success: false, 
            message: '서버 오류: ' + error.message 
        });
    }
});

// 일괄 배정 완료 알림 API
app.post('/api/notify-batch-complete', (req, res) => {
    try {
        const { totalProcessed, successCount, failCount, processedClaims } = req.body;
        
        const batchNotification = {
            type: 'batch_assign_notification',
            message: `📊 일괄 자동 배정이 완료되었습니다!`,
            batchData: {
                totalProcessed,
                successCount,
                failCount,
                processTime: new Date().toLocaleString(),
                processedClaims: processedClaims || []
            }
        };
        
        // 관리자급 직원들에게만 알림
        let notifiedCount = 0;
        connectedEmployees.forEach((employee) => {
            if (employee.ws.readyState === WebSocket.OPEN && 
                (employee.role === '관리자' || employee.userRole === 'EMPLOYEE')) {
                employee.ws.send(JSON.stringify(batchNotification));
                notifiedCount++;
            }
        });
        
        console.log(`📊 일괄 배정 완료 알림: ${totalProcessed}건 처리, ${notifiedCount}명에게 알림`);
        res.json({ 
            success: true, 
            message: `${notifiedCount}명의 관리자에게 알림 전송`,
            stats: { totalProcessed, successCount, failCount }
        });
        
    } catch (error) {
        console.error('일괄 배정 알림 API 오류:', error);
        res.status(500).json({ 
            success: false, 
            message: '서버 오류: ' + error.message 
        });
    }
});

// 수동 배정 알림 API
app.post('/api/notify-manual-assign', (req, res) => {
    try {
        const { 
            claimNo, 
            claimType, 
            claimContent,
            targetEmpNo, 
            assignedBy,
            priority 
        } = req.body;
        
        const targetEmployee = connectedEmployees.get(targetEmpNo);
        
        if (targetEmployee && targetEmployee.ws.readyState === WebSocket.OPEN) {
            targetEmployee.ws.send(JSON.stringify({
                type: 'manual_assigned_task',
                taskId: claimNo,
                message: '👨‍💼 관리자가 새로운 심사 작업을 배정했습니다.',
                taskData: {
                    claimNo,
                    claimType,
                    claimContent,
                    assignedEmpNo: targetEmpNo,
                    assignedEmpName: targetEmployee.empName,
                    assignTime: new Date().toLocaleString(),
                    assignType: 'MANUAL',
                    assignedBy,
                    priority: priority || 'NORMAL'
                }
            }));
            
            res.json({ 
                success: true, 
                message: '수동 배정 알림 전송 완료',
                targetEmployee: {
                    empNo: targetEmpNo,
                    empName: targetEmployee.empName
                }
            });
        } else {
            res.json({ 
                success: false, 
                message: '해당 직원이 접속하지 않음',
                targetEmpNo: targetEmpNo
            });
        }
    } catch (error) {
        console.error('수동 배정 알림 API 오류:', error);
        res.status(500).json({ 
            success: false, 
            message: '서버 오류: ' + error.message 
        });
    }
});

// 접속 상태 조회 API
app.get('/api/connection-status', (req, res) => {
    try {
        const status = {
            totalConnections: connectedEmployees.size,
            onlineEmployees: [],
            departmentSummary: {},
            roleSummary: {},
            serverInfo: {
                serverTime: new Date().toISOString(),
                uptime: process.uptime(),
                nodeVersion: process.version
            }
        };
        
        // 접속 중인 직원 정보 수집
        connectedEmployees.forEach((emp) => {
            if (emp.ws.readyState === WebSocket.OPEN) {
                status.onlineEmployees.push({
                    empNo: emp.empNo,
                    empName: emp.empName,
                    deptId: emp.deptId,
                    role: emp.role,
                    status: emp.status,
                    userRole: emp.userRole,
                    connectTime: emp.connectTime,
                    lastHeartbeat: emp.lastHeartbeat
                });
                
                // 부서별 집계
                const deptId = emp.deptId || '미분류';
                if (!status.departmentSummary[deptId]) {
                    status.departmentSummary[deptId] = { count: 0, employees: [] };
                }
                status.departmentSummary[deptId].count++;
                status.departmentSummary[deptId].employees.push(emp.empName);
                
                // 역할별 집계
                const role = emp.role || '미정';
                status.roleSummary[role] = (status.roleSummary[role] || 0) + 1;
            }
        });
        
        res.json(status);
        
    } catch (error) {
        console.error('접속 상태 조회 API 오류:', error);
        res.status(500).json({ 
            success: false, 
            message: '서버 오류: ' + error.message 
        });
    }
});



// ▼ [신규 추가] 결재 요청 알림 API ▼
app.post('/api/notify-approval-request', (req, res) => {
    const { claimNo, targetEmpNo, requesterName } = req.body;
    console.log(`📨 결재 요청 API 수신: ${claimNo}, 대상: ${targetEmpNo}, 요청자: ${requesterName}`);

    const targetEmployee = connectedEmployees.get(targetEmpNo);

    if (targetEmployee && targetEmployee.ws.readyState === WebSocket.OPEN) {
        targetEmployee.ws.send(JSON.stringify({
            type: 'approval_request',
            message: `${requesterName}님에게서 청구 건(No: ${claimNo})에 대한 결재 요청이 왔습니다.`
        }));
        res.json({ success: true, message: '결재 요청 알림 전송 완료' });
    } else {
        console.warn(`⚠️  결재 요청 실패: 관리자(${targetEmpNo})가 접속하지 않았습니다.`);
        res.json({ success: false, message: '관리자가 접속하지 않았습니다.' });
    }
});



// ▼ [신규 추가] 결재 결과 알림 API ▼
app.post('/api/notify-approval-result', (req, res) => {
    const { claimNo, targetEmpNo, approvalResult, approverName } = req.body;
    console.log(`📨 결재 결과 API 수신: ${claimNo}, 대상: ${targetEmpNo}, 결과: ${approvalResult}`);

    const targetEmployee = connectedEmployees.get(targetEmpNo);

    if (targetEmployee && targetEmployee.ws.readyState === WebSocket.OPEN) {
        targetEmployee.ws.send(JSON.stringify({
            type: 'approval_result',
            message: `신청하신 청구 건(No: ${claimNo})이 ${approverName}님에 의해 [${approvalResult}] 처리되었습니다.`
        }));
        res.json({ success: true, message: '결재 결과 알림 전송 완료' });
    } else {
        console.warn(`⚠️ 결재 결과 알림 실패: 실무자(${targetEmpNo})가 접속하지 않았습니다.`);
        res.json({ success: false, message: '해당 실무자가 접속하지 않았습니다.' });
    }
});


// ▼ [신규 추가] 보완완료 알림 API ▼
app.post('/api/notify-supplement-complete', (req, res) => {
    const { claimNo, targetEmpNo, message } = req.body;
    console.log(`📨 보완완료 API 수신: ${claimNo}, 대상: ${targetEmpNo}`);

    const targetEmployee = connectedEmployees.get(targetEmpNo);

    if (targetEmployee && targetEmployee.ws.readyState === WebSocket.OPEN) {
        targetEmployee.ws.send(JSON.stringify({
            type: 'supplement_complete',
            message: message || `청구 건(No: ${claimNo})의 보완서류가 제출되었습니다. 심사를 진행해주세요.`,
            taskData: {
                claimNo: claimNo,
                notificationType: 'supplement_complete',
                targetEmpNo: targetEmpNo
            }
        }));
        res.json({ success: true, message: '보완완료 알림 전송 완료' });
    } else {
        console.warn(`⚠️ 보완완료 알림 실패: 담당자(${targetEmpNo})가 접속하지 않았습니다.`);
        res.json({ success: false, message: '담당자가 접속하지 않았습니다.' });
    }
});

// ▼ [신규 추가] 고객 알림 API (고객이 청구 등록 시 사용) ▼
app.post('/api/notify-customer-action', (req, res) => {
    const { claimNo, targetEmpNo, actionType, customerName } = req.body;
    console.log(`📨 고객 액션 API 수신: ${claimNo}, 액션: ${actionType}, 대상: ${targetEmpNo}`);

    const targetEmployee = connectedEmployees.get(targetEmpNo);

    if (targetEmployee && targetEmployee.ws.readyState === WebSocket.OPEN) {
        let message = '';
        let notificationType = '';
        
        switch(actionType) {
            case 'new_claim':
                message = `${customerName}님이 새로운 청구(No: ${claimNo})를 등록했습니다.`;
                notificationType = 'customer_notification';
                break;
            case 'supplement_submit':
                message = `${customerName}님이 청구(No: ${claimNo})의 보완서류를 제출했습니다.`;
                notificationType = 'supplement_complete';
                break;
            default:
                message = `${customerName}님의 청구(No: ${claimNo})에 새로운 활동이 있습니다.`;
                notificationType = 'customer_notification';
        }
        
        targetEmployee.ws.send(JSON.stringify({
            type: notificationType,
            message: message,
            taskData: {
                claimNo: claimNo,
                customerName: customerName,
                actionType: actionType,
                targetEmpNo: targetEmpNo
            }
        }));
        
        res.json({ success: true, message: '고객 액션 알림 전송 완료' });
    } else {
        console.warn(`⚠️ 고객 액션 알림 실패: 담당자(${targetEmpNo})가 접속하지 않았습니다.`);
        res.json({ success: false, message: '담당자가 접속하지 않았습니다.' });
    }
});



// ==================== 서버 시작 ====================

// HTTP 서버 시작
app.listen(3000, () => {
    console.log('📡 HTTP API 서버 실행 (포트: 3000)');
    console.log('');
    console.log('🔗 사용 가능한 API:');
    console.log('   ├─ POST /api/notify-auto-assign        : 자동 배정 알림');
    console.log('   ├─ POST /api/notify-manual-assign      : 수동 배정 알림');
    console.log('   ├─ POST /api/notify-batch-complete     : 일괄 배정 완료 알림');
    console.log('   ├─ POST /api/notify-approval-request   : 결재 요청 알림');
    console.log('   ├─ POST /api/notify-approval-result    : 결재 결과 알림');
    console.log('   ├─ POST /api/notify-supplement-complete: 보완완료 알림');
    console.log('   ├─ POST /api/notify-customer-action    : 고객 액션 알림');
    console.log('   └─ GET  /api/connection-status         : 접속 상태 조회');
    console.log('');
    console.log('📊 연동 테이블: claim, employee, assign_rule, ins_dept, approval_req');
    console.log('🎯 지원 기능: 자동배정, 수동배정, 일괄배정, 결재처리, 보완완료, 실시간알림');
});

// 정리 작업
process.on('SIGINT', () => {
    console.log('\n🛑 실손보험 배정 시스템 웹소켓 서버 종료 중...');
    
    // 모든 웹소켓 연결에 종료 알림
    connectedEmployees.forEach((emp) => {
        if (emp.ws.readyState === WebSocket.OPEN) {
            emp.ws.send(JSON.stringify({
                type: 'server_shutdown',
                message: '서버가 점검을 위해 종료됩니다. 잠시 후 다시 연결해주세요.'
            }));
            emp.ws.close();
        }
    });
    
    console.log('👋 모든 연결이 정리되었습니다.');
    process.exit(0);
});

// 데드 커넥션 정리 (5분마다)
setInterval(() => {
    const fiveMinutesAgo = new Date(Date.now() - 5 * 60 * 1000);
    let removedCount = 0;
    
    connectedEmployees.forEach((emp, empNo) => {
        if (emp.ws.readyState !== WebSocket.OPEN || emp.lastHeartbeat < fiveMinutesAgo) {
            console.log(`🧹 비활성 연결 제거: ${emp.empName}(${empNo})`);
            connectedEmployees.delete(empNo);
            removedCount++;
        }
    });
    
    if (removedCount > 0) {
        console.log(`🧹 정리 완료: ${removedCount}개 연결 제거, 현재 ${connectedEmployees.size}개 연결 활성`);
    }
}, 5 * 60 * 1000); // 5분