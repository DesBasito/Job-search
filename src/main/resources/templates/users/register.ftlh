<#import "../layouts/layout.ftlh" as register>
<@register.layout>
<main>
    <form action="register" method="post">
        <fieldset>
            <p>Registration</p>
            <div class="form-element">
                <div class="row gx-5 gy-3">
                    <div class="col-6">
                        <input name="name" type="text" class="form-control" placeholder="First name"
                               aria-label="First name">
                    </div>
                    <div class="col-6">
                        <input name="surname" type="text" class="form-control" placeholder="Last name"
                               aria-label="Last name">
                    </div>
                    <div class="col-6">
                        <input name="email" type="email" class="form-control" placeholder="Email"
                               aria-label="Email">
                    </div>
                    <div class="col-6">
                        <input name="password" type="password" class="form-control" placeholder="Password"
                               aria-label="Password">
                    </div>
                    <div class="col-3">
                        <input name="age" type="number" class="form-control" placeholder="Age"
                               aria-label="age">
                    </div>
                    <div class="col-9">
                        <input name="phoneNumber" type="tel" class="form-control" placeholder="Phone number"
                               aria-label="phoneNumber">
                    </div>
                </div>
            </div>
            <div class="form-element mb-4">
                <label for="account-type" class="my-2">Account Type:</label>
                <select name="accType" id="account-type" class="form-control" required>
                    <option value="employee">employee</option>
                    <option value="employer">employer</option>
                </select>
            </div>

                <button class="btn btn-primary" type="submit">Register!</button>

                <button class="btn btn-warning btn-link text-decoration-none text-dark" onclick="window.location.href='/login'">
                    Have an account?
                </button>
        </fieldset>
    </form>
</main>
</@register.layout>