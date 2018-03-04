var join_check = {
	result:{	
		id: {
			empty:{ code:'empty',  desc:'아이디를 입력하세요(영문소문자, 숫자만 사용)'}, 
			space: { code:'space', desc:'공백없이 영문소문자, 숫자만 입력하세요' },
			invalid: { code:'invalid', desc:'아이디는 영문소문자, 숫자만 사용할 수 있습니다' },
			min: { code:'min', desc:'아이디는 6자 이상 입력합니다' },
			max: { code:'max', desc:'아이디는 10자 이하로 입력합니다' },
			valid: { code:'valid', desc:'아이디 중복여부를 확인하세요' },
			usable: { code:'usable', desc:'사용가능한 아이디입니다' },
			unusable: { code:'unusable', desc:'이미 사용중인 아이디입니다' }
		}, 
		pwd: {
			empty:{ code:'empty',  desc:'비밀번호를 입력하세요(영문 대/소문자, 숫자를 모두 포함)'}, 
			space: { code:'space', desc:'공백없이 대/소문자, 숫자를 입력하세요' },
			invalid: { code:'invalid', desc:'비밀번호는 대/소문자, 숫자만 사용할 수 있습니다' },
			min: { code:'min', desc:'비밀번호는 6자 이상 입력합니다' },
			max: { code:'max', desc:'비밀번호는 10자 이하로 입력합니다' },
			valid: { code:'valid', desc:'사용가능한 비밀번호입니다' },
			lack: {  code:'lack', desc:'비밀번호는 영문 대문자, 소문자, 숫자를 모두 포함해야 합니다' },
			retry: { code:'retry', desc:'비밀번호를 다시 입력하세요' },
			equal: { code:'valid', desc:'비밀번호가 일치합니다'},
			notequal: { code:'invalid', desc:'비밀번호가 일치하지 않습니다'}
		},
		
		email : {
			empty:{ code:'empty',  desc:'이메일을 입력하세요'},
			invalid:{ code:'invalid',  desc:'이메일이 유효하지 않습니다.'},
			valid: { code:'valid', desc:'이메일 중복여부를 확인하세요' },
			usable: { code:'usable', desc:'사용가능한 이메일입니다' },
			unusable: { code:'unusable', desc:'이미 사용중인 이메일입니다' }
		},
		
		birth : {
			empty:{ code:'empty',  desc:'생년월일을 선택하세요'},
			valid:{ code:'valid',  desc:'유효한 생년월일입니다.'}
		}
	}, 
	
	email_status : function( email ){
var reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
		if( email=="" ){
			return this.result.email.empty;
		}else if ( !reg.test( email ) ){ //이메일 형식에 맞지 않는 경우
			return this.result.email.invalid;
		}else{
			return this.result.email.valid;
		}
	},
	
	email_usable : function( use ){
		if( use=="true" ){
			return this.result.id.usable;
		}else{
			return this.result.id.unusable;
		}
	},
	
	pwd_check : function( pwd_ck, pwd ){
		if( pwd_ck=="" ){ //입력하지 않은 경우
			return this.result.pwd.retry;
		}else if( pwd_ck == pwd ){ // 비밀번호가 일치하는 경우
			return this.result.pwd.equal;			
		}else{// 비밀번호가 일치하지 않는 경우
			return this.result.pwd.notequal;		
		}
	},
	
	pwd_status : function( pwd ){
		var space = /\s/g;
		var reg =/[^a-zA-Z0-9]/g;
		var lower = /[a-z]/g;
		var upper = /[A-Z]/g;
		var digit = /[0-9]/g;
		if( pwd=="" ){ //입력하지 않은 경우
			return this.result.pwd.empty;
		}else if ( pwd.match(space) ){ //공백이 있는 경우
			return this.result.pwd.space;
		}else if ( reg.test(pwd) ){ //영문 대/소문자, 숫자 외의 문자가 있는 경우
			return this.result.pwd.invalid;
		}else if ( pwd.length<6 ){ // 최소입력갯수 미만인 경우: 6이상10이하가 유효길이
			return this.result.pwd.min;
		}else if ( pwd.length>10 ){.0
			return this.result.pwd.max;
		}else if ( !lower.test(pwd) | !upper.test(pwd) | !digit.test(pwd) ) {
			return this.result.pwd.lack;
		}else{
			return this.result.pwd.valid;
		}
	},
	
	id_status : function( id ){
		var space = /\s/g;
		var reg = /[^a-z0-9]/g;
		if( id=="" ){ //입력하지 않은 경우
			return this.result.id.empty;
		}else if ( id.match(space) ){ //공백이 있는 경우
			return this.result.id.space;
		}else if ( reg.test(id) ){ //영문소문자, 숫자 외의 문자가 있는 경우
			return this.result.id.invalid;
		}else if ( id.length<6 ){ // 최소입력갯수 미만인 경우: 6이상10이하가 유효길이
			return this.result.id.min;
		}else if ( id.length>10 ){
			return this.result.id.max;
		}else{
			return this.result.id.valid;
		}
	},
		
	id_usable : function( use ){
		if( use=="true" ){
			return this.result.id.usable;
		}else{
			return this.result.id.unusable;
		}
	},
	
	birth_status : function( birth ){
		if( birth==""){
			return this.result.birth.empty;
		}else{
			return this.result.birth.valid;
		}
		
	}
	
}