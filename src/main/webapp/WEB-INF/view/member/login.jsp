<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/top.jsp" %>

<!-- layout Content -->
<th:block layout:fragment="content">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-5">
        <div class="card shadow-lg border-0 rounded-lg mt-5">
          <div class="card-header">
            <h3 class="text-center font-weight-bold my-4">Login</h3>
          </div>
          <div class="card-body">
            <form id="frm" name="frm" action="/login/action" method="post">
              <!-- <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"> -->
              <div th:if="${param.error}">
                <p th:text="${exception}" class="alert alert-danger"></p>
              </div>
              <div class="form-floating mb-3">
                <input class="form-control" id="username" name="username" type="email" placeholder="name@example.com" required />
              </div>
              <div class="form-floating mb-3">
                <input class="form-control" id="password" name="password" type="password" placeholder="Password" required />
              </div>
              <div class="form-check mb-3">
                <input class="form-check-input" id="remember-me" name="remember-me" type="checkbox" />
                <label class="form-check-label" for="remember-me">Remember Password</label>
              </div>
              <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                <!-- <a class="small" href="javascript:">Forgot Password?</a> -->
                <a class="btn btn-primary" href="javascript:fnSubmit();">Login</a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script th:inline="javascript">


    function fnSubmit() {
      let frm = $("#frm");
      frm.submit();
    }

    $(function() {
      $("#password").on("keyup", function(e) {
        if (e.key == "Enter") fnSubmit();
      });

      // frm.validate({
      //   submitHandler: function(form) {
      //     // Submit Action..
      //     form.submit();
      //   }
      // });
    });
  </script>
</th:block>
</html>