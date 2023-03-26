function focus(elementId){
  let element = document.getElementById(elementId);
  element.classList.add("focus");
}

function blur(elementId){
  let element = document.getElementById(elementId);

  element.classList.remove("focus");

}
