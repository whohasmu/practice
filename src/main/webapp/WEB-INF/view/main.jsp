<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/top.jsp" %>

this is main ! @@@@
<button id="testButton">click</button>
<div id="testDiv">

</div>


<script type="text/javascript">
    $(document).ready(function(){

        $('#testButton').click(function () {
            let params = {
                // 'name' : '길동',
                // 'text' : 'test',
            }
            $.ajax({
                url: '/api/hello',
                type: 'get',
                data : params,
                // data : JSON.stringify(params),
                contentType: "application/json; charset=UTF-8",
                dataType : 'json',
            })
            .done(function(data){
                console.log('done');
                console.log('done data : ',data);
                $('#testDiv').html(data);



                // if(data.succeeded !== undefined && data.succeeded === false){
                //     if(data.authException !== undefined && data.authException === true){
                //         location.replace("/logout.do");
                //     }
                // }
            })
            .fail(function(xhr, status, error) {
                console.log('error');
                console.log('xhr : ',xhr);
                // console.log('status : ',status);
                // console.log('error : ',error);

                if(xhr.status === 401){
                    location.replace("/logout");
                }else if(xhr.status === 403){
                    // window.location = '/api/denied?exception=' + xhr.responseJSON.message;
                }

                $.each(xhr.responseJSON, function(index, error){
                    // console.log('test , ',error);
                    $('#testDiv').html(error);
                });
            });


        });

    });



</script>
</html>