/**
 * Created by Mirage on 27/02/2017.
 */


function post(path) {

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", path);

    document.body.appendChild(form);
    form.submit();
}

function logout(){
    post("/logout")
}

/* LEFT FIX MENU*/
$('#sidebar').affix({
    offset: {
        top: 0
    }
});

