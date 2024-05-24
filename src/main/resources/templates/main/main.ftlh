<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="row">
        <div class="col-md-8">
            <form id="searchVaca" class="d-flex my-3">
                <input class="form-control" type="search" placeholder="<@spring.message 'live.search'/>" aria-label="Search" name="vacancy" data-search>
            </form>
            <div id="page-list">
            </div>
            <nav aria-label="Page navigation example" id="paging-buttons">
            </nav>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><@spring.message 'main.filter'/></h5>
                        <form action="/vacancies/filter" method="get">
                        <div class="mb-3">
                            <label for="category" class="form-label"><@spring.message 'main.category'/>:</label>
                            <select name="category" id="category" class="form-control" required>
                                <#list categories as category>
                                    <option value=${category.name}>${category.name}</option>
                                </#list>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary"><@spring.message 'main.sort'/></button>
                    </form>
                </div>
            </div>
            <div class="card card-body mt-2">
                    <form action="/" method="get">
                    <h5 class="card-title"><@spring.message 'main.sort.vacancies'/></h5>
                    <div class="my-3">
                        <select class="form-select" name="filter" aria-label="Default select example">
                            <option selected value=null><@spring.message 'selected'/></option>
                            <option value="ascDate"><@spring.message 'main.date.ascending'/></option>
                            <option value="descDate"><@spring.message 'main.date.descending'/></option>
                            <option value="ascApplies"><@spring.message 'main.applies.ascending'/></option>
                            <option value="descApplies"><@spring.message 'main.applies.descending'/></option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary"><@spring.message 'main.sort'/></button>
                </form>
            </div>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script>
        const sortingBy = '${filter}'
        const vacancyPage = '<@spring.message 'page'/>'
        const negotiable = '<@spring.message 'negotiable'/>'
    </script>

    <script src="/js/main.js"></script>
</@main.layout>