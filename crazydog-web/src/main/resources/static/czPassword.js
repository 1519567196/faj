window.addEventListener("load", function () {

    var app = new Vue({
        el: '#czpass-regi',
        data: {
            noBlank: true,
            noSame: true
        },
        computed: {
            isPass: function () {
                if (this.noBlank || this.noSame) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    });
    window.app = app;
    // 校验密码
    var phoneZhpa = document.getElementById("pass-czpa");
    if (phoneZhpa) {
        phoneZhpa.addEventListener("blur", function () {
            var rtiPhone = document.getElementById("zti-passw");
            if (rtiPhone) {
                if (this.value == "") {
                    app.noBlank = true;
                    rtiPhone.innerText = "密码不能为空";
                    rtiPhone.classList.add("active-rti");
                } else {
                    app.noBlank = false;
                    rtiPhone.classList.remove("active-rti");
                }
            }
        });
    }

    // 校验再次输入密码
    var apasCzpa = document.getElementById("apas-czpa");
    if (apasCzpa) {
        apasCzpa.addEventListener("blur", function () {
            var ztiApass = document.getElementById("zti-apass");
            if (ztiApass) {
                var password = document.getElementById("pass-czpa");
                if (this.value == "") {
                    app.noBlank = true;
                    ztiApass.innerText = "密码不能为空";
                    ztiApass.classList.add("active-rti");
                } else if (password && password.value != this.value) {
                    app.noSame = true;
                    ztiApass.innerText = "密码输入不一致";
                    ztiApass.classList.add("active-rti");
                } else {
                    app.noSame = false;
                    app.noBlank = false;
                    ztiApass.classList.remove("active-rti");
                }
            }
        });
    }
});