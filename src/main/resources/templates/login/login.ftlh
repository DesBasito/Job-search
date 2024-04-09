<#import "../layout.ftlh" as register>
<@register.layout>
    <main>
        <form action="/login" method="post">
            <fieldset>
                <p>Registration</p>
                <div class="form-element mb-5">
                    <div class="row ">
                        <div class="col">
                            <input name="email" type="email" class="form-control" placeholder="Email"
                                   aria-label="Email">
                        </div>
                        <div class="col">
                            <input name="password" type="password" class="form-control" placeholder="Password"
                                   aria-label="Password">
                        </div>
                    </div>
                </div>

                <button class="btn btn-primary" type="submit">Login!</button>

                <a class="btn btn-warning btn-link text-decoration-none text-dark" onclick="window.location.href='/register'">
                    Have an account?
                </a>
            </fieldset>
        </form>
    </main>
</@register.layout>