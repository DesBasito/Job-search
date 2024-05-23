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
                            <#--                            <input type="text" class="form-control" id="resumeName" name="title" required>-->
                            <@spring.formInput "resumeCreateDto.title" 'class="form-control" id="resumeName" name="title" '/>
                            <@spring.showErrors "<br>" "error text-danger"/>
                        </div>
                        <div class="col">
                            <label for="category" class="form-label"><@spring.message 'category'/>:</label>
                            <select name="categoryName" id="category" class="form-control" required>
                                <option selected></option>
                                <#if categories??>
                                    <#list categories as category>
                                        <option value="${category.name}">${category.name}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                        <div class="col">
                            <label for="salary" class="form-label"><@spring.message 'salary'/></label>
                            <#--                            <input type="text" class="form-control" id="salary" name="salary" required>-->
                            <@spring.formInput "resumeCreateDto.salary" 'class="form-control" id="salary" name="salary" '/>
                            <@spring.showErrors "<br>" "error text-danger"/>
                        </div>
                    </div>
                </div>

                <div class="card mb-4" id="workExp">
                    <h5 class="card-title text-center"><@spring.message 'resume.work.experience'/></h5>
                    <div class="d-flex justify-content-center">
                        <input id="add-work-btn" class="btn btn-success my-3" type="button"
                               value="<@spring.message 'resume.add.work.exp'/>">
                    </div>
                    <div id="work" class="d-flex justify-content-center">
                        <#if resumeCreateDto.workExpInfo??>
                            <#assign w = resumeCreateDto.workExpInfo?size/>
                            <#list resumeCreateDto.workExpInfo as workExpInfo>
                                <div class="card-body" id="work-${workExpInfo?index}">
                                    <div class="mb-3">
                                        <label class="form-label"><@spring.message 'resume.company'/></label>
                                        <@spring.formInput "resumeCreateDto.workExpInfo[${workExpInfo?index}].companyName" 'class="form-control"'/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label"><@spring.message 'resume.position'/></label>
                                        <@spring.formInput "resumeCreateDto.workExpInfo[${workExpInfo?index}].position" 'class="form-control"'/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label"><@spring.message 'resume.responsibilities'/></label>
                                        <@spring.formTextarea "resumeCreateDto.workExpInfo[${workExpInfo?index}].responsibilities" 'class="form-control"'/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label"><@spring.message 'resume.years'/></label>
                                        <@spring.formInput "resumeCreateDto.workExpInfo[${workExpInfo?index}].years" 'class="form-control"'/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    </div>
                                    <button class="btn btn-danger card-close" type="button"
                                            onclick="removeByWorkId('work-${workExpInfo?index}')"><@spring.message 'resume.remove'/></button>
                                </div>
                            </#list>
                        </#if>
                    </div>
                </div>


                <div class="card mb-4" id="education">
                    <h5 class="card-title text-center"><@spring.message 'resume.education'/></h5>
                    <div class="d-flex justify-content-center">
                        <input id="add-education-btn" class="btn btn-success my-3" type="button"
                               value="<@spring.message 'resume.add.education'/>">
                    </div>
                    <div id="educationInfo" class="d-flex justify-content-center">
                        <#if resumeCreateDto.educationInfo??>
                            <#assign e = resumeCreateDto.educationInfo?size/>
                            <#list resumeCreateDto.educationInfo as educationInfo>
                                <div class="card-body" id="education-${educationInfo?index}">
                                    <div class="mb-3">
                                        <label class="form-label"><@spring.message 'resume.degree'/></label>
                                        <@spring.formInput "resumeCreateDto.educationInfo[${educationInfo?index}].degree" 'class="form-control"'/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label"><@spring.message 'resume.institution'/></label>
                                        <@spring.formInput "resumeCreateDto.educationInfo[${educationInfo?index}].institution" 'class="form-control"'/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label"><@spring.message 'resume.program'/></label>
                                        <@spring.formTextarea "resumeCreateDto.educationInfo[${educationInfo?index}].program" 'class="form-control"'/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label"><@spring.message 'resume.startDate'/></label>
                                        <@spring.formInput "resumeCreateDto.educationInfo[${educationInfo?index}].startDate" 'class="form-control" type="date"'/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label"><@spring.message 'resume.endDate'/></label>
                                        <@spring.formInput "resumeCreateDto.educationInfo[${educationInfo?index}].endDate" 'class="form-control" type="date"'/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    </div>
                                    <button class="btn btn-danger card-close" type="button"
                                            onclick="removeByEducationId('education-${educationInfo?index}')"><@spring.message 'resume.remove'/></button>
                                </div>
                            </#list>
                        </#if>
                    </div>
                </div>

                <div class="card mb-4" id="addContacts">
                    <div class="card-body">
                        <h5 class="card-title"><@spring.message 'resume.contacts'/></h5>
                        <div class="mb-3">
                            <input type="hidden" value="Instagram" name="contacts[0].type">
                            <div class="mb-3">
                                <label for="inst" class="form-label">Instagram </label>
                                <@spring.formInput "resumeCreateDto.contacts[0].infoValue" 'class="form-control" id="inst" name="contacts[0].infoValue'/>
                                <@spring.showErrors "<br>" "error text-danger"/>
                            </div>
                        </div>

                        <div class="mb-3">
                            <input type="hidden" value="Telegram" name="contacts[1].type">
                            <div class="mb-3">
                                <label for="telega" class="form-label">Telegram</label>
                                <@spring.formInput "resumeCreateDto.contacts[1].infoValue" 'class="form-control"  id="telega" name="contacts[1].infoValue"'/>
                                <@spring.showErrors "<br>" "error text-danger"/>
                            </div>
                        </div>

                        <div class="mb-3">
                            <input type="hidden" value="Facebook" name="contacts[2].type">
                            <div class="mb-3">
                                <label for="face" class="form-label">Facebook </label>
                                <@spring.formInput "resumeCreateDto.contacts[2].infoValue" 'class="form-control"  id="face" name="contacts[2].infoValue"'/>
                                <@spring.showErrors "<br>" "error text-danger"/>
                            </div>
                        </div>

                        <div class="mb-3">
                            <input type="hidden" value="LinkedIn" name="contacts[3].type">
                            <div class="mb-3">
                                <label for="link" class="form-label">LinkedIn</label>
                                <@spring.formInput "resumeCreateDto.contacts[2].infoValue" 'class="form-control"  id="link" name="contacts[3].infoValue"'/>
                                <@spring.showErrors "<br>" "error text-danger"/>
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
        function removeByEducationId(education) {
            let elem = document.getElementById(education);
            console.log(elem);
            elem.remove();
        }

        function removeByWorkId(param) {
            let elem = document.getElementById(param);
            console.log(elem);
            elem.remove();
        }


        let lastWorkExpId = 0;
        <#if w??>
        lastWorkExpId = ${w};
        console.log(lastWorkExpId)
        </#if>
        let lastEducationId = 0;
        <#if e??>
        lastEducationId = ${e};
        console.log(lastEducationId)
        </#if>
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

