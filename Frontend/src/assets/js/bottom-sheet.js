function showSheet(){
  let container = document.getElementById('bottomSheetContainer');
  let bottomSheet = document.getElementById('bottomSheet');

  console.log(22)


  container.classList.add('active');
  setTimeout(() => {
    bottomSheet.classList.add('active');

  })
}

function hideSheet(){
  let container = document.getElementById('bottomSheetContainer');
  let bottomSheet = document.getElementById('bottomSheet');

    bottomSheet.classList.remove('active');

    setTimeout(() => {
      container.classList.remove('active');

    }, 400)
}
