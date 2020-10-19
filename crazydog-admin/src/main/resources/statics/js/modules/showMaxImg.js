function hideMax(){
    $(".MAX_div").remove();
    $("#Cover_Div").hide();
 }
    function showMax(url){
        $("#Cover_Div").show();
        var Image=function(){
            return document.createElement("img");
        }  
        var DIV=function(){
            return document.createElement("div");
        }          
        var div=new DIV();    
        var close_div=new DIV();
        var close_img=new Image();    
        var img=new Image();
        var _enter=false;
            $(".MAX_div").remove();
            div.setAttribute("class","MAX_div");
            close_div.setAttribute("class","close");
            close_div.setAttribute("title","点击关闭");
             
            
            img.setAttribute("class","showMax");
            img.src=url;
            close_img.src="../../statics/img/close.jpg";
            img.onmouseover=function(){
                _enter=true;
            }
            img.onmouseout=function(){
                _enter=false;
            }
            close_div.onclick=function(){
                hideMax();
            }
            close_div.appendChild(close_img);
            div.appendChild(img);
            div.appendChild(close_div);
            document.body.appendChild(div);
            
            //var _z=9999;
            var close=$(".close");
            $(document).ready(function(){
             var _move=false;//移动标记
             var _x,_y;//鼠标离控件左上角的相对位置
             var wd;//窗口
                $(".showMax").click(function(){
                    //alert("click");//点击（松开后触发）
                 //this.style.cursor = "default";//鼠标形状
                 //this.style.zIndex = 999;
                    }).mousedown(function(e){
                    _move=true;
                    wd=$(this);
                    
                    //this.style.cursor = "move";//鼠标形状
                   // this.style.zIndex = _z;//窗口层次
                   // _z++;
                    _x=e.pageX-(isNaN(parseInt(wd.css("left")))?0:parseInt(wd.css("left")));
                    _y=e.pageY-(isNaN(parseInt(wd.css("top")))?0:parseInt(wd.css("top")));       

                    c_x=e.pageX-(isNaN(parseInt(close.css("left")))?0:parseInt(close.css("left")));
                    c_y=e.pageY-(isNaN(parseInt(close.css("top")))?0:parseInt(close.css("top"))); 
                    
                    /*  wd.fadeTo(20, 0.25); *///点击后开始拖动并透明显示
                    $(document).mousemove(function(e){
                        if(_move){
                            var x=e.pageX-_x;//移动时根据鼠标位置计算控件左上角的绝对位置
                            var y=e.pageY-_y;

                            var closeX=e.pageX-c_x;
                            var closeY=e.pageY-c_y;
                            wd.css({top:y,left:x});//控件新位置
                            close.css({top:closeY,left:closeX});
                        }
                    }).mouseup(function(){
                    _move=false;
                    /* wd.fadeTo("fast", 1); *///松开鼠标后停止移动并恢复成不透明
                  });
                });
                
            });
           
            //$(".showMax").mouseover(function(){
                
                var scrollTop = $(window).scrollTop();
                var initTop=scrollTop;

                var screenheight = window.screen.availHeight; //获取屏幕高   
                $(window).scroll( function() { 
                    scrollTop = $(window).scrollTop();;
                    var w=parseInt($(".showMax").css("width"));
                    var h=parseInt($(".showMax").css("height"));
                    
                        if(scrollTop > initTop&&_enter){
                            $(".showMax").css({"width":w+Number(28)+"px","height":h+Number(50)+"px"});
                            close.css("margin-left",(w-125)<125?125:(w-125)+"px");
                            $(window).scrollTop(initTop);//保持滚动条距离底部0px 
                        } 
                        if(scrollTop <initTop&&_enter){
                            $(".showMax").css({"width":(w-28)<280?280:(w-28)+"px","height":(h-50)<500?500:(h-50)+"px"});
                            close.css("margin-left",(w-155-28)<125?125:(w-155-28)+"px");
                            $(window).scrollTop(initTop);//保持滚动条距离底部0px 
                        }
                    
                    //initTop=scrollTop;   
                });
            //})
            
            
            
            
            function imgdragstart(){return false;}  
            for(i in document.images)document.images[i].ondragstart=imgdragstart; 
        }