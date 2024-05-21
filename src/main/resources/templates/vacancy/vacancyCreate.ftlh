<#import "../layout.ftlh" as main>
<@main.layout;spring>
    <h1 class="mb-4">Create Resume</h1>

    <main>

        <#if _csrf??>
            <form action="/vacancies/create" method="POST" enctype="application/x-www-form-urlencoded">
                <!-- Card for Resume Details Section -->
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title"><@spring.message 'vacancy.details'/></h5>
                        <div class="row">
                            <div class="col-md-6">
                                <label for="vacancyName" class="form-label"><@spring.message 'vacancy.name'/></label>
                                <@spring.formInput "vacancyCreateDto.name" 'class="form-control" id="vacancyName" name="name" required'/>
                                <@spring.showErrors "<br>" "error text-danger"/>
                                <#--                                <input type="text" class="form-control" id="vacancyName" name="name" required>-->
                            </div>
                            <div class="col-md-6">
                                <label for="category" class="form-label"><@spring.message 'category'/>:</label>
                                <select name="category" id="category" class="form-control" required>
                                    <option selected></option>
                                    <#if categories??>
                                        <#list categories as category>
                                            <option value="${category.name}">${category.name}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="salary" class="form-label"><@spring.message 'salary'/></label>
                                <@spring.formInput "vacancyCreateDto.salary" 'class="form-control" id="salary" name="salary" required'/>
                                <@spring.showErrors "<br>" "error text-danger"/>
                                <#--                                <input type="text" class="form-control" id="salary" name="salary" required>-->
                            </div>
                            <div class="col-md-6">
                                <label for="description"
                                       class="form-label"><@spring.message 'vacancy.description'/></label>
                                <@spring.formTextarea "vacancyCreateDto.description" 'class="form-control" id="description" name="description" required'/>
                                <@spring.showErrors "<br>" "error text-danger"/>
                                <#--                                <textarea class="form-control" id="description" name="description" required></textarea>-->
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label for="expFromId"
                                           class="form-label"><@spring.message 'vacancy.experience.from'/></label>
                                    <@spring.formInput "vacancyCreateDto.expFrom" 'class="form-control" type="number" id="expFromId" name="expFrom"'/>
                                    <@spring.showErrors "<br>" "error text-danger"/>
                                    <#--                                    <input type="number" class="form-control" id="expFromId" name="expFrom">-->
                                </div>
                                <div class="col-md-6">
                                    <label for="expToId"
                                           class="form-label"><@spring.message 'vacancy.experience.to'/></label>
                                    <@spring.formInput "vacancyCreateDto.expTo" 'class="form-control" type="number" id="expFromId" name="expTo"'/>
                                    <@spring.showErrors "<br>" "error text-danger"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                       value="${(_csrf.token)!'--no-token--'}"/>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary btn-lg"><@spring.message 'create'/></button>
                </div>
            </form>
        </#if>
    </main>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</@main.layout>

