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
                        <form action="/resume/edit/${resume.id}" method="post">
                            <div class="card-body">
                                <h3 class="card-title mb-4">
                                    <label>
                                        <input type="text" class="form-control" name="title" value="${resume.title}"
                                               placeholder="Resume Name">
                                    </label></h3>
                                <p class="card-text text-muted">
                                    <strong><@spring.message 'category'/>:</strong>
                                    <label>
                                        <input type="text"
                                               name="categoryName"
                                               list="datalistOptions"
                                               value="${resume.categoryName}"
                                               placeholder="<@spring.message 'category'/>">
                                    </label>
                                    <datalist id="datalistOptions">
                                        <#list categories as category>
                                        <option value=${category.name}>
                                            </#list>
                                    </datalist>
                                </p>
                                <p class="card-text text-muted"><strong><@spring.message 'salary'/>:</strong> <label>
                                        <input type="text"
                                               name="salary"
                                               value="${resume.salary}"
                                               placeholder="Salary">
                                    </label>
                                </p>

                                <hr class="my-4"> <!-- Horizontal line -->

                                <h5 class="card-subtitle mb-3 text-muted"><@spring.message 'resume.work.experience'/></h5>
                                <div class="work-experience">
                                    <#list resume.workExpInfoEdit as work>
                                        <div class="mb-5">
                                            <h6>
                                                <label>
                                                    <input name="workExpInfoEdit[${work_index}].id" value="${work.id}"
                                                           hidden="hidden">
                                                </label>
                                                <label>
                                                    <input type="text" name="workExpInfoEdit[${work_index}].position" value="${work.position}"
                                                           placeholder="<@spring.message 'resume.position'/>">
                                                </label> - <label>
                                                    <input type="text" name="workExpInfoEdit[${work_index}].companyName"
                                                           value="${work.companyName}"
                                                           placeholder="<@spring.message 'resume.company'/>">
                                                </label></h6>
                                            <p><label>
                                                    <textarea name="workExpInfoEdit[${work_index}].responsibilities"
                                                              placeholder="<@spring.message 'resume.responsibilities'/>">${work.responsibilities}</textarea>
                                                </label>
                                            </p>
                                            <p class="text-muted"><@spring.message 'resume.worked'/>: <label>
                                                    <input type="text" name="workExpInfoEdit[${work_index}].years"
                                                           placeholder="<@spring.message 'edit.resume.years'/>">
                                                </label> <@spring.message 'resume.years'/></p>
                                        </div>
                                    </#list>
                                    <#assign workExpLastIndex = resume.workExpInfoEdit?size - 1>
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
                                    <#list resume.educationInfo as edu>
                                        <div class="mb-3">
                                            <label>
                                                <input name="educationInfo[${edu_index}].id" value="${edu.id}"
                                                       placeholder="id" disabled>
                                            </label>
                                            <h6><label>
                                                    <input type="text" name="educationInfo[${edu_index}].degree" value="${edu.degree}"
                                                           placeholder="<@spring.message 'resume.degree'/>">
                                                </label>, <label>
                                                    <input type="text" name="educationInfo[${edu_index}].institution"
                                                           placeholder="<@spring.message 'resume.institution'/>">
                                                </label></h6>
                                            <p><label>
<textarea name="educationInfo[${edu_index}].program"
          placeholder="<@spring.message 'resume.program'/>">${edu.program}</textarea>
                                                </label></p>
                                            <p class="text-muted"><label>
                                                    <input type="date" name="educationInfo[${edu_index}].startDate"
                                                           value="${edu.startDate}"
                                                           placeholder="<@spring.message 'resume.startDate'/>">
                                                </label> - <label>
                                                    <input type="date"
                                                           name="educationInfo[${edu_index}].endDate"
                                                           value="${edu.endDate}"
                                                           placeholder="<@spring.message 'resume.endDate'/>">
                                                </label>
                                        </div>
                                    </#list>
                                    <#assign eduLastIndex = resume.educationInfo?size-1>
                                </div>
                                <div id="education">
                                    <div id="educationInfo" class="d-flex justify-content-center my-4">
                                        <input id="add-education-btn" class="btn btn-success my-3" type="button" value="<@spring.message 'resume.add.education'/>">
                                    </div>
                                </div>

                                <hr class="my-4"> <!-- Horizontal line -->
                                <div class="d-grid gap-2">
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                           value="${(_csrf.token)!'--no-token--'}"/>
                                    <button type="submit" class="btn btn-primary btn-md"><@spring.message 'profile.edit'/></button>
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
        const workExpLastIndex = ${workExpLastIndex};
        const eduLastIndex = ${eduLastIndex};
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
