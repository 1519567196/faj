/**
 * 从 file 域获取 本地图片 url
 */
function getFileUrl(obj) {
    let url;
    url = window.URL.createObjectURL(obj.files.item(0));
    return url;
}

export default {
    name: 'accident',
    // 定义数据
    data () {
        return {
            imgNum:4,    //上传的照片数量，可根据实际情况自定义
        }
    },//定义事件
    methods:{
        //根据点击上传按钮触发input
        change_input(){
            let inputArr=$('#addTextForm input');
            let add_inputId='';     //需要被触发的input
            for(let i=0;i<inputArr.length;i++){
                // 根据input的value值判断是否已经选择文件
                if(!inputArr[i].value){          //如果没有选择,获得这个input的ID
                    add_inputId=inputArr[i].id;
                    break;
                }
            }
            if(add_inputId){                   //如果需要被触发的input ID存在,将对应的input触发
                return  $("#"+add_inputId).click();
            }else{
                alert("最多选择"+this.imgNum+"张图片")
            }
        },
        //当input选择了图片的时候触发,将获得的src赋值到相对应的img
        setImg(e){
            let target=e.target;
            $('#img_'+target.id).attr('src',getFileUrl(e.srcElement));
        },
        //点击图片删除该图片并清除相对的input
        deleteImg(e){
            let target=e.target;
            let inputID='';       //需要清除value的input
            if(target.nodeName=='IMG'){
                target.src='';
                inputID=target.id.replace('img_','');    //获得需要清除value的input
                $('input#'+inputID).val("");
            }
        },
        //提交信息到后台
        submit(){
            $("#addTextForm").ajaxSubmit({
                url: this.$root.api+"/Index/staff_accident/add",
                type: "post",
                data: {
                    'total_price':this.price,
                    'descript':this.descript,
                },
                success:  (data) => {
                    if(data.code==0){
                        console.log(‘提交成功’);
                        $("#addTextForm input").val('');

                        $('div#img-wrapper img').attr('src','');
                    }else{
                        alert('提交失败');
                    }
                }
            });
        }
    },
    //页面加载后执行
    mounted(){
        for(let i=0;i<this.imgNum;i++){
            //生成input框，默认为1
            let my_input = $('<input type="file" name="image" />');   //创建一个input
            my_input.attr('id',i);                           //为创建的input添加id
            $('#addTextForm').append(my_input);                     //将生成的input追加到指定的form
            //生成img，默认为1
            let my_img = $('<img src="">');
            my_img.attr('id', 'img_'+i);
            my_img.css({"max-width":"50%","max-height":"200px"});   //添加样式，由于vue的执行机制，页面加载的时候img标签还没有生成，直接写在style样式会不生效
            $('#img-wrapper').append(my_img);
        }
    },
}