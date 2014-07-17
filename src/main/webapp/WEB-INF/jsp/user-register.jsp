<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>

<form:form commandName="user" cssClass="form-horizontal registrationForm">

    <c:if test="${param.success eq true}">
        <div class="alert alert-success">Registration successful!</div>
    </c:if>

    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Name:</label>
        <div class="col-sm-10">
            <form:input path="name" cssClass="form-control" />
            <form:errors path="name"/>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">Email:</label>
        <div class="col-sm-10">
            <form:input path="email" cssClass="form-control" />
            <form:errors path="email" />
        </div>
    </div>

    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password:</label>
        <div class="col-sm-10">
            <form:password path="password" cssClass="form-control" />
            <form:errors path="password" />
        </div>
    </div>
    
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password again:</label>
        <div class="col-sm-10">
            <input type="password" name="password_again" id="password_again" class="form-control"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-2">
            <input type="Submit" value="Save"
                class="btn btn-lg btn-primary" />
        </div>
    </div>
</form:form>

<script type="text/javascript">
    $(document).ready(function() {
        $(".registrationForm").validate({
            rules : {
                name : {
                    required : true,
                    minlength : 3,
                    remote : {
                        url : "<spring:url value='/register/available.html'/>",
                        type : "get",
                        data : {
                            userName : function() {
                                return $("#name").val();
                            }
                        }
                    }
                },
                email : {
                    required : true,
                    email : true
                },
                password : {
                    required : true,
                    minlength : 6
                },
                password_again : {
                    required : true,
                    minlength : 6,
                    equalTo : "#password"
                }
            },
            highlight : function(element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            unhighlight : function(element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            messages : {
                name : {
                    remote : "Such username already exists!"
                }
            }
        });
    });
</script>
