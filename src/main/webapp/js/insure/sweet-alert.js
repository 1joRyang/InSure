const Toast = Swal.mixin({
	    toast: true,
	    showConfirmButton: false,
	})

scwin.toastAlertMobile = (param) => {
	Toast.fire({
		...param, 
		timer: 2000,
		position: 'center',
		timerProgressBar: false,
	});
}
	
scwin.toastAlert = (param) => {
	Toast.fire({
		...param, 
		timer: 3000,
		position: 'top-end',
		timerProgressBar: true,
		didOpen: (toast) => {
			        toast.addEventListener('mouseenter', Swal.stopTimer)
			        toast.addEventListener('mouseleave', Swal.resumeTimer)
			    }
	});
} 

scwin.noBtnAlert = (param) => {
	Swal.fire({
		...param,
		showConfirmButton: false,
	});
}

scwin.noBtnAlertWithTimer = (param) => {
	Swal.fire({
		...param,
		timer: 2000,
		showConfirmButton: false,
	});
}

/** 부르는 형태 예시
scwin.twoBtnAlert({
     title: "로그아웃 하시겠습니까?",
 }, (result) => {
     if (result.isConfirmed) {
         $c.sbm.execute(sbm_EmployeeLogout);
     }
 });
 */
scwin.twoBtnAlert = (param, func) => {
	Swal.fire({
		...param,
		showCancelButton: true,
		confirmButtonColor: '#4FACFE',
		cancelButtonColor: '#d1d1d1',
		confirmButtonText: '완료',
		cancelButtonText: '취소',
		reverseButtons: true
	}).then(func);
}
