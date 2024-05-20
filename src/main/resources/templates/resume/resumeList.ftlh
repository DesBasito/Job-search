<#import "../layout.ftlh" as resumes>
<@resumes.layout; spring>
    <div class="row">
        <div class="col-md-8">
            <div id="resume-pageList"></div>
            <nav aria-label="Page navigation example" id="resume-pageButtons">
            </nav>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><@spring.message 'resume.filter'/></h5>
                    <#if _csrf??>
                        <form action="/resume/filter" method="get">
                            <div class="mb-3">
                                <label for="category" class="form-label"><@spring.message 'resume.filter'/>:</label>
                                <input type="text" list="datalistOptions" class="form-control" id="category"
                                       name="category"
                                       placeholder="<@spring.message 'main.name.category'/>">
                                <datalist id="datalistOptions">
                                    <#list categories as category>
                                    <option value=${category.name}>
                                        </#list>
                                </datalist>
                            </div>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                   value="${(_csrf.token)!'--no-token--'}"/>
                            <button type="submit" class="btn btn-primary"><@spring.message 'resume.sort'/></button>
                        </form>
                    </#if>
                </div>
                <div class="card card-body mt-2">
                    <#if _csrf??>
                        <form action="/resume" method="get">
                            <h5 class="card-title"><@spring.message 'resume.filter'/></h5>
                            <div class="my-3">
                                <select class="form-select" name="filter" aria-label="Default select example">
                                    <option value="ascDate"><@spring.message 'resume.date.ascending'/></option>
                                    <option value="descDate"><@spring.message 'resume.date.descending'/></option>
                                </select>
                            </div>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                   value="${(_csrf.token)!'--no-token--'}"/>
                            <button type="submit" class="btn btn-primary"><@spring.message 'resume.filter'/></button>
                        </form>
                    </#if>
                </div>
            </div>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script>
        const resumeSortingBy = '${filter}'
    </script>
    <script src="/js/resumesPage.js"></script>
</@resumes.layout>