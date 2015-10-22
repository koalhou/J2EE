function getMonthList(eleID) {  
	    var statisMonth =document.getElementById(eleID);  
	    var date = new Date();  
	    var startYear = 2011;  
	    var startMonth = 1;  
	    var curYear = date.getFullYear();  
	    var curMonth = date.getMonth() + 1;  
	    if (curMonth == 1&&startYear==curYear) {  
	        curYear--;  
	        curMonth = 12;  
	    } else {  
	        curMonth;  
	    }  
	    
	    var k=0;
	    var list = new Array();  
	    for (var i = startYear; i <= curYear; i++) {  
	        for (var j = 1; j <= 12; j++) {  
		       
	            if (((i == startYear && j < startMonth) || (i == curYear && j > curMonth))) {
		        } else {
		        	
		        	if(i==curYear-1&&curMonth<j){
		        		var monthStr = "";  
		                if (j < 10){  
		                    monthStr = "0" + j;  
		                }else{  
		                    monthStr = j;  
		                }     
		                var yearMonth = i +"-"+ monthStr;  
		                list[k] = yearMonth;
		                k++;
		        	}
		        	if(i==curYear){
		        		var monthStr = "";  
		                if (j < 10){  
		                    monthStr = "0" + j;  
		                }else{  
		                    monthStr = j;  
		                }     
		                var yearMonth = i +"-"+ monthStr;  
		                list[k] = yearMonth;
		                k++;
		        	}
	            }  
	        }  
	    }     
	    list = list.reverse();  
	    
	    for(var k=0;k<list.length;k++){  
	    	
			var newoption = document.createElement("OPTION");  
			newoption.text = list[k];  
			newoption.value = list[k];  
			//statisMonth.options.add(newoption);
			statisMonth.options.add(newoption);  
			//statisMonth.options.appendChild(newoption);  
			//statisMonth.options.length++;  
			//statisMonth.options[i].value = list[i];  
			//statisMonth.options[i].text = list[i];  
			//if(i+1 == curMonth){  
			//statisMonth.options[i].selected = true;  
			//}   
	    }         
	}  