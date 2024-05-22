<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <h1><@spring.message 'vacancy.edit'/></h1>
    <main>
        <#if _csrf??>
            <form action="/vacancies/edit?id=${vacancy.id}" method="post">
                <label for="name"><@spring.message 'vacancy.name'/>:</label><br>
                <@spring.formInput "vacancyEditDto.name" 'class="form-control" id="name" name="name" placeholder="${vacancy.name}"'/>
                <@spring.showErrors "<br>" "error text-danger"/>
                <br>
                <label for="description"><@spring.message 'vacancy.description'/>:</label><br>
                <@spring.formTextarea "vacancyEditDto.description" 'class="form-control" id="description" name="description" placeholder="${vacancy.description}"'/>
                <@spring.showErrors "<br>" "error text-danger"/>
                <br>
                <label for="category"><@spring.message 'category'/>:</label><br>
                <select name="category" id="category" class="form-control" required>
                    <option selected></option>
                    <#if categories??>
                        <#list categories as category>
                            <option value="${category.name}">${category.name}</option>
                        </#list>
                    </#if>
                </select>
                <br>
                <label for="salary"><@spring.message 'salary'/>:</label><br>
                <@spring.formInput "vacancyEditDto.salary" 'class="form-control" id="salary" name="salary"
               placeholder="${vacancy.salary}"'/>
                <@spring.showErrors "<br>" "error text-danger"/>
                <br>
                <label for="expFrom"><@spring.message 'vacancy.experience.from'/>:</label><br>
                <@spring.formInput "vacancyEditDto.expFrom" 'class="form-control" min="0" max="40" type="number" id="expFromId" name="expFrom" placeholder="${vacancy.expFrom}"'/>
                <@spring.showErrors "<br>" "error text-danger"/>
                <br>
                <label for="expTo"><@spring.message 'vacancy.experience.to'/>:</label><br>
                <@spring.formInput "vacancyEditDto.expTo" 'class="form-control" min="0" max="40" type="number" id="expTo" name="expTo" placeholder="${vacancy.expTo}"'/>
                <@spring.showErrors "<br>" "error text-danger"/>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                       value="${(_csrf.token)!'--no-token--'}"/>
                <button type="submit"><@spring.message 'profile.update'/></button>
            </form>
        </#if>
    </main>
</@main.layout>
