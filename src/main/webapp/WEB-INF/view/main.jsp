<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
this is main !
<button id="testButton">click</button>
<div id="testDiv">

</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
    $(document).ready(function(){

        $('#testButton').click(function () {
            $.ajax({
                url: '/api/hello',
                type: 'get',
                // , data : JSON.stringify(param)
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dataType : 'text',
            })
            .done(function(data){
                console.log('done');
                console.log('done data : ',data);
                $('#testDiv').html(data);

                if(data.succeeded !== undefined && data.succeeded === false){
                    if(data.authException !== undefined && data.authException === true){
                        location.replace("/logout.do");
                    }
                }
            })
            .fail(function(xhr, status, error) {
                console.log('error');
                console.log('xhr : ',xhr);
                console.log('status : ',status);
                console.log('error : ',error);

                if(xhr.status === 401){
                    location.replace("/logout");
                }else if(xhr.status === 403){
                    // window.location = '/api/denied?exception=' + xhr.responseJSON.message;
                }
            });


        });

    });



</script>
</html>