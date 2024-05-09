<#import "../layout.ftlh" as register>
<@register.layout ; spring>
    <main>
        <#if _csrf??>
            <form action="/register" method="post">
                <fieldset>
                    <p>Registration</p>
                    <div class="form-element">
                        <div class="row gx-5 gy-3">
                            <div class="col-6">
                                <@spring.formInput "userCreationDto.name" 'class="form-control" placeholder="First name" aria-label="First name"'/>
                                <@spring.showErrors "<br><hr>" "error text-danger"/>
<#--                                <input name="name" type="text" class="form-control" placeholder="First name"-->
<#--                                       aria-label="First name">-->
                            </div>
                            <div class="col-6">
                                <@spring.formInput "userCreationDto.surname" 'class="form-control" placeholder="Surname" aria-label="surname"'/>
                                <@spring.showErrors "<br><hr>" "error text-danger"/>
                                <#--<input name="surname" type="text" class="form-control" placeholder="Last name"-->
<#--                                       aria-label="Last name">-->
                            </div>
                            <div class="col-6">
                                <@spring.formInput "userCreationDto.email" 'class="form-control" placeholder="email" aria-label="Email"'/>
                                <@spring.showErrors "<br><hr>" "error text-danger"/>
<#--                                <input name="email" type="email" class="form-control" placeholder="Email"-->
<#--                                       aria-label="Email">-->
                            </div>x
                            <div class="col-6">
                                <@spring.formPasswordInput "userCreationDto.password" 'class="form-control" placeholder="password" aria-label="password"'/>
                                <@spring.showErrors "<br><hr>" "error text-danger"/>
<#--                                <input name="password" type="password" class="form-control" placeholder="Password"-->
<#--                                       aria-label="Password">-->
                            </div>
                            <div class="col-3">
                                <@spring.formInput "userCreationDto.age" 'class="form-control" placeholder="age" aria-label="age"'/>
                                <@spring.showErrors "<br><hr>" "error text-danger"/>
<#--                                <input name="age" type="number" class="form-control" placeholder="Age"-->
<#--                                       aria-label="age">-->
                            </div>
                            <div class="col-9">
                                <@spring.formInput "userCreationDto.phoneNumber" 'class="form-control" placeholder="phone number" aria-label="phoneNumber"'/>
                                <@spring.showErrors "<br><hr>" "error text-danger"/>
<#--                                <input name="phoneNumber" type="tel" class="form-control" placeholder="Phone number"-->
<#--                                       aria-label="phoneNumber">-->
                            </div>
                        </div>
                    </div>
                    <div class="form-element mb-4">
                        <label for="account-type" class="my-2">Account Type:</label>
                        <select name="accType" id="account-type" class="form-control" required>
                            <option value=1>employee</option>
                            <option value=2>employer</option>
                        </select>
                    </div>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                    <button class="btn btn-primary" type="submit">Register!</button>

                    <a class="btn btn-warning btn-link text-decoration-none text-dark"
                       onclick="window.location.href='/login'">
                        Have an account?
                    </a>
                </fieldset>
            </form>
        </#if>
    </main>
</@register.layout>