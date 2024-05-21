<#import "../layout.ftlh" as main>
<@main.layout;spring>
    <h1 class="mb-4">Create Resume</h1>

    <main>
<#if _csrf??>
        <form action="/resume/create" method="POST">
            <div class="card mb-4">
                <div class="card-body">
                    <h5 class="card-title"><@spring.message 'resume.details'/></h5>
                        <div class="col">
                            <label for="resumeName" class="form-label"><@spring.message 'resume.name'/></label>
                            <input type="text" class="form-control" id="resumeName" name="title" required>
                        </div>
                        <div class="col">
                            <label for="category" class="form-label"><@spring.message 'category'/>:</label>
                            <input type="text" list="datalistOptions" class="form-control" id="category"
                                   name="<@spring.message 'category'/>"
                                   placeholder="<@spring.message 'main.name.category'/>">
                            <datalist id="datalistOptions">
                                <#list categories as category>
                                <option value=${category.name}>
                                    </#list>
                            </datalist>
                        </div>
                        <div class="col">
                            <label for="salary" class="form-label"><@spring.message 'salary'/></label>
                            <input type="text" class="form-control" id="salary" name="salary" required>
                        </div>
                    </div>
                </div>

            <div class="card mb-4" id="workExp">
                <h5 class="card-title text-center"><@spring.message 'resume.work.experience'/></h5>
                <div id="work" class="d-flex justify-content-center">
                    <input id="add-work-btn" class="btn btn-success my-3" type="button" value="<@spring.message 'resume.add.work.exp'/>">
                </div>
            </div>


            <div class="card mb-4" id="education">
                    <h5 class="card-title text-center"><@spring.message 'resume.education'/></h5>
                <div id="educationInfo" class="d-flex justify-content-center">
                    <input id="add-education-btn" class="btn btn-success my-3" type="button" value="<@spring.message 'resume.add.education'/>">
                </div>
            </div>

            <div class="card mb-4" id="addContacts">
                <div class="card-body">
                    <h5 class="card-title"><@spring.message 'resume.contacts'/></h5>
                    <div class="mb-3">
                        <input type="hidden" value="Instagram" name="contacts[0].type">
                        <div class="mb-3">
                            <label for="inst" class="form-label">Instagram </label>
                            <input type="text"  class="form-control" id="inst" name="contacts[0].infoValue">
                        </div>
                    </div>

                    <div class="mb-3">
                        <input type="hidden" value="Telegram" name="contacts[1].type">
                        <div class="mb-3">
                            <label for="telega" class="form-label">Telegram</label>
                            <input type="text"  class="form-control" id="telega" name="contacts[1].infoValue">
                        </div>
                    </div>

                    <div class="mb-3">
                        <input type="hidden" value="Facebook" name="contacts[2].type">
                        <div class="mb-3">
                            <label for="face" class="form-label">Facebook </label>
                            <input type="text"  class="form-control" id="face" name="contacts[2].infoValue">
                        </div>
                    </div>

                    <div class="mb-3">
                        <input type="hidden" value="LinkedIn" name="contacts[3].type">
                        <div class="mb-3">
                            <label for="face" class="form-label">LinkedIn</label>
                            <input type="text"  class="form-control" id="face" name="contacts[3].infoValue">
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script>
        let position = '<@spring.message 'resume.position'/>';
        let years = '<@spring.message 'resume.worked.years'/>';
        let company = '<@spring.message 'resume.company'/>';
        let responsibilities = '<@spring.message 'resume.responsibilities'/>';
        let remove = '<@spring.message 'resume.remove'/>';
        let degree = '<@spring.message 'resume.degree'/>';
        let program = '<@spring.message 'resume.program'/>';
        let institution = '<@spring.message 'resume.institution'/>';
        let startDate = '<@spring.message 'resume.startDate'/>';
        let endDate = '<@spring.message 'resume.endDate'/>'
    </script>
    <script src="/js/createResume.js"></script>
</@main.layout>

