<#import "../layout.ftlh" as main>
<@main.layout;spring>
    <div class="container py-5">
        <div class="row">
            <div class="col-md-4">
                <#if user.avatar??>
                    <img src="/api/users/image/${user.avatar}" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                <#else >
                    <img src="/api/users/image/anon.jpeg" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                </#if>
                <div class="user-info">
                    <h4>${user.name} ${user.surname}</h4>
                    <p class="text-muted"><strong><@spring.message 'age'/>:</strong> ${user.age}</p>
                    <p class="text-muted"><strong><@spring.message 'phone.number'/>:</strong> ${user.phoneNumber}</p>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card border-0 shadow-lg">
                    <main>
                        <#if _csrf??>
                            <form action="/resume/edit/${editResumeDto.id}" method="post">
                                <div class="card-body">
                                    <h3 class="card-title mb-4"></h3>
                                        <label for="resumeName" class="form-label"></label>
                                        <@spring.formInput "editResumeDto.title" 'class="form-control" id="resumeName" value="${editResumeDto.title}" name="title" '/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    <p class="card-text text-muted">
                                        <strong><@spring.message 'category'/>:</strong>
                                        <label for="category" class="form-label"><@spring.message 'category'/>:</label>
                                        <select name="categoryName" id="category" class="form-control" required>
                                            <option selected></option>
                                            <#if categories??>
                                                <#list categories as category>
                                                    <option value="${category.name}">${category.name}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </p>
                                    <p class="card-text text-muted"><strong><@spring.message 'salary'/>:</strong>
                                        <label for="salary" class="form-label"><@spring.message 'salary'/></label>
                                        <@spring.formInput "editResumeDto.salary" 'class="form-control" id="salary" name="salary" '/>
                                        <@spring.showErrors "<br>" "error text-danger"/>
                                    </p>

                                    <hr class="my-4"> <!-- Horizontal line -->

                                    <h5 class="card-subtitle mb-3 text-muted"><@spring.message 'resume.work.experience'/></h5>
                                    <div class="work-experience">
                                        <#if editResumeDto.workExpInfoEdit??>
                                            <#assign w = editResumeDto.workExpInfoEdit?size/>
                                            <#list editResumeDto.workExpInfoEdit as workExpInfoEdit>
                                                <div class="card-body" id="work-${workExpInfoEdit?index}">
                                                    <#if workExpInfoEdit.id??>
                                                        <input type="hidden" name="workExpInfoEdit[${workExpInfoEdit?index}].id" value="${workExpInfoEdit.id}">
                                                        <input type="hidden" name="workExpInfoEdit[${workExpInfoEdit?index}].delete" id="work-delete-${workExpInfoEdit?index}" value="false">
                                                    </#if>
                                                    <div class="mb-3">
                                                        <label class="form-label"><@spring.message 'resume.company'/></label>
                                                        <@spring.formInput "editResumeDto.workExpInfoEdit[${workExpInfoEdit?index}].companyName" 'class="form-control"'/>
                                                        <@spring.showErrors "<br>" "error text-danger"/>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label"><@spring.message 'resume.position'/></label>
                                                        <@spring.formInput "editResumeDto.workExpInfoEdit[${workExpInfoEdit?index}].position" 'class="form-control"'/>
                                                        <@spring.showErrors "<br>" "error text-danger"/>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label"><@spring.message 'resume.responsibilities'/></label>
                                                        <@spring.formTextarea "editResumeDto.workExpInfoEdit[${workExpInfoEdit?index}].responsibilities" 'class="form-control"'/>
                                                        <@spring.showErrors "<br>" "error text-danger"/>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label"><@spring.message 'resume.years'/></label>
                                                        <@spring.formInput "editResumeDto.workExpInfoEdit[${workExpInfoEdit?index}].years" 'class="form-control"'/>
                                                        <@spring.showErrors "<br>" "error text-danger"/>
                                                    </div>
                                                    <#if workExpInfoEdit.id??>
                                                        <#else >
                                                            <button class="btn btn-danger card-close" type="button"
                                                                    onclick="markForDeletion('work', ${workExpInfoEdit?index})"><@spring.message 'resume.remove'/></button>
                                                    </#if>
                                                </div>
                                            </#list>
                                            <#assign workExpLastIndex = editResumeDto.workExpInfoEdit?size - 1>
                                        </#if>
                                    </div>
                                    <div id="work">
                                        <div class="d-flex justify-content-center my-4">
                                            <input id="add-work-btn" class="btn btn-success mt-3 mb-1" type="button"
                                                   value="<@spring.message 'resume.add.work.exp'/>">
                                        </div>
                                    </div>
                                    <hr class="my-4"> <!-- Horizontal line -->

                                    <h5 class="card-subtitle mb-3 text-muted"><@spring.message 'resume.education'/></h5>
                                    <div class="education">
                                        <#if editResumeDto.educationInfo??>
                                            <#assign e = editResumeDto.educationInfo?size/>
                                            <#list editResumeDto.educationInfo as educationInfo>
                                                <div class="card-body" id="educationEdit-${educationInfo?index}">
                                                    <#if educationInfo.id??>
                                                        <input type="hidden" name="educationInfo[${educationInfo?index}].id" value="${educationInfo.id}">
                                                        <input type="hidden" name="educationInfo[${educationInfo?index}].delete" id="education-delete-${educationInfo?index}" value="false">
                                                    </#if>
                                                    <div class="mb-3">
                                                        <label class="form-label"><@spring.message 'resume.degree'/></label>
                                                        <@spring.formInput "editResumeDto.educationInfo[${educationInfo?index}].degree" 'class="form-control"'/>
                                                        <@spring.showErrors "<br>" "error text-danger"/>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label"><@spring.message 'resume.institution'/></label>
                                                        <@spring.formInput "editResumeDto.educationInfo[${educationInfo?index}].institution" 'class="form-control"'/>
                                                        <@spring.showErrors "<br>" "error text-danger"/>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label"><@spring.message 'resume.program'/></label>
                                                        <@spring.formTextarea "editResumeDto.educationInfo[${educationInfo?index}].program" 'class="form-control"'/>
                                                        <@spring.showErrors "<br>" "error text-danger"/>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label"><@spring.message 'resume.startDate'/></label>
                                                        <@spring.formInput "editResumeDto.educationInfo[${educationInfo?index}].startDate" 'class="form-control" type="date"'/>
                                                        <@spring.showErrors "<br>" "error text-danger"/>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label"><@spring.message 'resume.endDate'/></label>
                                                        <@spring.formInput "editResumeDto.educationInfo[${educationInfo?index}].endDate" 'class="form-control" type="date"'/>
                                                        <@spring.showErrors "<br>" "error text-danger"/>
                                                    </div>
                                                    <#if educationInfo.id??>
                                                    <#else >
                                                        <button class="btn btn-danger card-close" type="button"
                                                                onclick="markForDeletion('education', ${educationInfo?index})"><@spring.message 'resume.remove'/></button>
                                                    </#if>
                                                </div>
                                            </#list>
                                            <#assign eduLastIndex = editResumeDto.educationInfo?size-1>
                                        </#if>
                                    </div>
                                    <div id="education">
                                        <div id="educationInfo" class="d-flex justify-content-center my-4">
                                            <input id="add-education-btn" class="btn btn-success my-3" type="button"
                                                   value="<@spring.message 'resume.add.education'/>">
                                        </div>
                                    </div>

                                    <hr class="my-4"> <!-- Horizontal line -->
                                    <div class="d-grid gap-2">
                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                               value="${(_csrf.token)!'--no-token--'}"/>
                                        <button type="submit"
                                                class="btn btn-primary btn-md"><@spring.message 'profile.edit'/></button>
                                    </div>
                                </div>
                            </form>
                        </#if>
                    </main>

                </div>
            </div>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script>
        function markForDeletion(type, index) {
            if (type === 'work') {
               let work = document.getElementById(`work-`+index);
                work.remove();
            } else if (type === 'education') {
                let edu = document.getElementById(`educationEdit-`+index);
                edu.remove();
            }
        }
        let workExpLastIndex = 0;
        <#if workExpLastIndex??>
        workExpLastIndex = ${workExpLastIndex};
        </#if>
        let eduLastIndex = 0;
        <#if eduLastIndex??>
        eduLastIndex = ${eduLastIndex};
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
    <script src="/js/editResume.js"></script>

    <link rel="stylesheet" href="/resumeInfo.css">
</@main.layout>
