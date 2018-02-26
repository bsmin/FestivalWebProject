var festival_info = {
	infos:{	
		agelimit: '',
		discountinfofestival:'',
		eventhomepage:'',
		eventplace:'',
		placeinfo:'',
		playtime:'',
		program:'',
		sponsor:'',
		sponsor_tel:'',
		subevent:'',
		usetimefestival:'' 
	},

	detail_info : function(agelimit,discountinfofestival,eventhomepage,eventplace,placeinfo,playtime,program,sponsor1,sponsor1tel,sponsor2,sponsor2tel,subevent,usetimefestival){
		console.log("sadsad"+agelimit);
		console.log("sadsad"+eventhomepage);
		console.log("sadsad"+program);
		console.log("sadsad"+sponsor1);
		console.log("sadsad"+sponsor1tel);
		console.log("sadsad"+subevent);
		console.log("sadsad"+usetimefestival);
		console.log("sadsad"+playtime);
		
		if(agelimit != null){
	        this.infos.agelimit = agelimit;
		}

	    if(discountinfofestival != null){
	       this.infos.discountinfofestival = discountinfofestival;
	    }
	    if(eventhomepage != null){
	    	this.infos.eventhomepage = eventhomepage;
	    }
	    if(eventplace != null){
	    	this.infos.eventplace = eventplace;
	    }
	    if(placeinfo != null){
	    	this.infos.placeinfo = placeinfo;
	    }
	    if(playtime != null){
	    	this.infos.playtime = playtime;
	    }
	    if(program != null){
	    	this.infos.program = program;
	    }
	    if(sponsor2 != null && sponsor2 != ""){
	    	this.infos.sponsor = sponsor2;
	    	this.infos.sponsor_tel = sponsor2tel;
	    }else if(sponsor1 != null ){
	    	this.infos.sponsor = sponsor1;
	    	this.infos.sponsor_tel = sponsor1tel;
	    }
	    if(subevent != null){
	    	this.infos.subevent = subevent;
	    }
	    if(usetimefestival != null){
	    	this.infos.usetimefestival = usetimefestival;
	    }
//	    console.log("agelimit " +this.infos.agelimit);
//	    console.log("sponsor " +this.infos.sponsor);
//	    console.log("placeinfo " +this.infos.placeinfo);
//	    console.log("eventplace " +this.infos.eventplace);
	    return this.infos;
	}
}
