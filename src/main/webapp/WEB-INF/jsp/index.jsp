<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/taglib.jsp" %>

<h1>Latest News</h1>

    <table class="table table-bordered table-striped table-hover">
        <thead>
          <tr>
            <th>Date</th>
            <th>Item</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${items}" var="item">
            <tr>
              <td>
                <c:out value="${item.publishedDate}" />
                <br />
                <c:out value="${item.blog.name}" />
              </td>
              <td>
                <strong>
                    <a href="<c:out value="${item.link}" />" target="_blank">
                        <c:out value="${item.title}" />
                    </a>
                </strong>
                <br/>
                ${item.description}                
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
