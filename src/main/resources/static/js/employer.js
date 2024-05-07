// const employerCardElement = document.getElementById('employer');
// const employerPagingButtons = document.getElementById('employer-pageButtons');
// const urlEmployerVacancies = `api/vacancies/`;
// let employerPageNum = 0;
// window.onload = async () => {
//     await fetchAndRenderVacancies(urlEmployerVacancies);
//     employerPagingButtons.innerHTML = `
//        <ul class="pagination">
//             <li class="page-item page-link" style="cursor: pointer" onclick="switchPageEmployer(pageNum - 1, 'previous')">Previous</li>
//             <li class="page-item page-link" style="cursor: pointer" onclick="switchPageEmployer(1, '1')">1</a></li>
//             <li class="page-item page-link" style="cursor: pointer" onclick="switchPageEmployer(2, '2')">2</a></li>
//             <li class="page-item page-link" style="cursor: pointer" onclick="switchPageEmployer(3, '3')">3</a></li>
//             <li class="page-item page-link" style="cursor: pointer" onclick="switchPageEmployer(pageNum + 1, 'next')">Next</li>
//         </ul>
//     `
// };
//
// async function fetchAndRenderVacancies(url) {
//     const email = userEmail;
//     try {
//         const response = await fetch(url+email+`/paging`);
//         const dataVacancies = await response.json();
//         console.log(dataVacancies)
//
//         if (dataVacancies && Array.isArray(dataVacancies.content)) {
//             renderVacancies(dataVacancies.content);
//         } else {
//             console.error('Invalid data format received from the server');
//         }
//     } catch (error) {
//         console.error('Error fetching or processing data:', error);
//     }
// }
//
// function renderVacancies(vacancies) {
//     employerCardElement.innerHTML = '';
//     vacancies.forEach(vacancy => {
//         const card = createVacancyCard(vacancy);
//         employerCardElement.innerHTML += card;
//     });
// }
//
// function createVacancyCard(vacancy) {
//     return `
//         <div class="col mb-3">
//                             <div class="card">
//                                 <div class="card-body">
//                                     <h5 class="card-title"><a href="/vacancies/info/${vacancy.id}"
//                                                               class="text-decoration-none text-dark">${vacancy.name}</a>
//                                     </h5>
//                                     <p class="card-text">Last update: ${vacancy.updateTime})</p>
//                                 </div>
//                             </div>
//                             <div class="card-footer d-flex justify-content-between gx-2 my-2">
//                                 <div>
//                                     <main>
//                                         <form action="/vacancies/edit?id=${vacancy.id}" method="post">
//                                             <button type="submit" class="btn btn-primary" style="width: 150px">Update
//                                             </button>
//                                         </form>
//                                     </main>
//                                 </div>
//
//                                 <div>
//                                     <button type="button" class="btn btn-success " style="width: 150px"
//                                             onclick="window.location.href='/vacancies/edit?id=${vacancy.id}'">
//                                         Edit
//                                     </button>
//                                 </div>
//                             </div>
//                         </div>
//
//     `;
// }
//
// async function switchPageEmployer(pageNumber, sortingBy, pageFunc) {
//     switch (pageFunc) {
//         case 'previous':
//             employerPageNum = Math.max(0, pageNum - 1);
//             break;
//         case 'next':
//             employerPageNum++;
//             break;
//         default:
//             employerPageNum = parseInt(pageFunc);
//             break;
//     }
//     const url = `${urlEmployerVacancies}?page=${employerPageNum}&filter=${sortingBy}`;
//     await fetchAndRenderVacancies(url);
// }
