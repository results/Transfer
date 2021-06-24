function showMessage(title,message) {
    document.getElementById("alert-overlay").style.display = "block";
    document.getElementById("alert-overlay-text").innerHTML = message;
    document.getElementById("alert-overlay-header").innerHTML = title;
  }
  
  function closeMessage() {
    document.getElementById("alert-overlay").style.display = "none";
  }

  function redirectionWarn(newurl) {
    document.getElementById("alert-overlay-footer").addEventListener("click", function() {
        redirect(newurl);
    });
    document.getElementById("alert-overlay-footer").innerHTML = "<hr><a style='text-align: center; color: bg-primary; text-decoration: none;' class='show-pointer center'><span class='inline-icon-small material-icons show-pointer'>logout</span> Continue To Website</a>";
    showMessage("Redirection","You have clicked a link that will redirect away from our website. We cannot guarantee the security or content of the website you are about to visit. If you do not wish to leave this website, click the exit in the top right corner. Clicl 'Continue To Website' to acknowledge this message and be redirected.");
  }


  function redirect(newurl,seconds) {
    setTimeout(function(){
        window.location.href = "http://"+newurl;
    }, seconds*1000);
  }

  function formatPhoneNumber(phoneNumberString) {
    var cleaned = ('' + phoneNumberString).replace(/\D/g, '');
    var match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
    if (match) {
      return '(' + match[1] + ') ' + match[2] + '-' + match[3];
    }
    return null;
  }

  function redirectLocal(relative,seconds) {
    setTimeout(function(){
        window.location.href = relative;
    }, seconds*1000);
  }