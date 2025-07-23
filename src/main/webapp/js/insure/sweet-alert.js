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

scwin.twoBtnAlert = (param) => {
	Swal.fire({
		...param,
		showCancelButton: true,
		confirmButtonColor: '#4FACFE',
		cancelButtonColor: '#d1d1d1',
		confirmButtonText: '완료',
		cancelButtonText: '취소',
		reverseButtons: true
	});
}
