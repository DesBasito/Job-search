<#import "../layout.ftlh" as login>
<@login.layout>
    <form id="login-form" action="/login" method="post">
        <p>Registration</p>
        <div class="form-element mb-5">
            <input  name="username" type="email" id="username" class="form-control mb-3" placeholder="Email"
                   aria-label="Email">
            <input name="password" type="password" class="form-control mb-3" placeholder="Password"
                   aria-label="Password">
        <div id="error-input" style="color: red"></div>
        </div>

        <button class="btn btn-primary" type="submit">Login!</button>

        <a class="btn btn-warning btn-link text-decoration-none text-dark" onclick="window.location.href='/register'">
            Have an account?
        </a>
    </form>
    <script src="/js/login.js"></script>
</@login.layout>