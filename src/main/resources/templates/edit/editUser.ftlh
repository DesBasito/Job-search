<#import "../layout.ftlh" as register>
<@register.layout;spring>
    <main>
        <#if _csrf??>
        <#if accType == 'employee'>
        <form action="/applicant/edit" method="post">
            <#else>
            <form action="/employer/edit" method="post">
                </#if>
                <fieldset>
                    <div class="form-element">
                        <div class="row gx-5 gy-3">
                            <div class="col-6">
                                <input name="name" type="text" class="form-control" placeholder="<@spring.message 'edit.first.name'/>"
                                       aria-label="First name">
                            </div>
                            <div class="col-6">
                                <input name="surname" type="text" class="form-control" placeholder="<@spring.message 'edit.surname'/>"
                                       aria-label="Last name">
                            </div>
                            <div class="col-3">
                                <input name="age" type="number" class="form-control" placeholder="<@spring.message 'edit.age'/>"
                                       aria-label="age">
                            </div>
                            <div class="col-12">
                                <input name="phoneNumber" type="tel" class="form-control" placeholder="<@spring.message 'edit.phone'/>"
                                       aria-label="phoneNumber">
                            </div>
                        </div>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                        <button class="btn btn-primary mt-5" style="width: 200px" type="submit"><@spring.message 'profile.edit'/></button>
                </fieldset>
            </form>
            </#if>
    </main>
</@register.layout>