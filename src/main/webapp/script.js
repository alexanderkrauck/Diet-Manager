var SwitchEnum = {
    LOGIN: 1,
    HOME: 2,
    MY_HISTORY: 3,
    TODAY: 4,
    PROFILE: 5
};

var login = false;
var register = false;
var page = SwitchEnum.HOME;

var key;
var height;
var weight;
var gender;
var birthdate;
var dailyEnergy;
var alreadyEatenEnergy = 0;

var switchHome;
var switchLogin;
var switchToday;
var switchMyHistory;
var switchProfile;
var buttonCreateAccount;
var buttonLogin;
var buttonToLogin;

var sectionHome;
var sectionLogin;
var sectionToday;
var sectionProfile;

var contentinner;

var loginEmailGroup;
var loginUsernameInput;
var loginPasswordInput;
var loginEmailInput;

var profileHeightInput;
var profileWeightInput;
var profileSelectDay;
var profileSelectMonth;
var profileSelectYear;
var profileButtonMale;
var profileButtonFemale;
var profileButtonSave;

var todayButtonCalories;
var todayButtonFood;
var todayInputCalories;
var todayDivCalories;
var piechart;
var todayTbodyEaten;

function onLoad() {
    alert("bla");
    switchHome = $("#switch_home");
    switchLogin = $("#switch_login");
    switchToday = $("#switch_today");
    switchMyHistory = $("#switch_my_history");
    switchProfile = $("#switch_profile");
    sectionHome = $("#section_home");
    sectionLogin = $("#section_login");
    sectionToday = $("#section_today");
    sectionProfile = $("#section_profile");
    contentinner = $("#contentinner");
    loginEmailGroup = $("#login_email_group");
    buttonLogin = $("#button_login");
    buttonCreateAccount = $("#button_create_account");
    buttonToLogin = $("#button_to_login");
    loginUsernameInput = $("#login_username_input");
    loginPasswordInput = $("#login_password_input");
    loginEmailInput = $("#login_email_input");
    profileHeightInput = $("#profile_height_input");
    profileWeightInput = $("#profile_weight_input");
    profileSelectDay = $("#profile_select_day");
    profileSelectMonth = $("#profile_select_month");
    profileSelectYear = $("#profile_select_year");
    profileButtonMale = $("#profile_button_male");
    profileButtonFemale = $("#profile_button_female");
    profileButtonSave = $("#profile_button_save");
    todayDivCalories = $("#today_div_calories");
    todayButtonCalories = $("#today_button_calories");
    todayButtonFood = $("#today_button_food");
    todayInputCalories = $("#today_input_calories");
    piechart = $("#piechart");
    todayTbodyEaten = $("#today_tbody_eaten");


    loginEmailInput.val("");
    loginPasswordInput.val("");
    loginUsernameInput.val("");
    switchMyHistory.fadeOut(0);
    switchToday.fadeOut(0);
    switchProfile.fadeOut(0);

    todayButtonFood.addClass("profile_button_gender_not");
    todayButtonCalories.text("ADD");

    sectionLogin.remove();
    sectionToday.remove();
    sectionProfile.remove();
    loginChange(false);
    for (i = 0; i < 100; i++) {
        var y = new Date().getYear() - i + 1900;
        var add = "<option>" + y + "</option>"
        profileSelectYear.append(add);
    }

    switchHome.click(function () {
        switchPage(SwitchEnum.HOME, sectionHome);
    });
    switchToday.click(function () {
        switchPage(SwitchEnum.TODAY, sectionToday);
    });
    switchLogin.click(function () {
        switchPage(SwitchEnum.LOGIN, sectionLogin);
        if (login) {
            //LOGOUT
            loginChange(false);
        }
    });
    switchProfile.click(function () {
        switchPage(SwitchEnum.PROFILE, sectionProfile);
    });
}


var loginChange = function (loginVar) {
    login = loginVar;
    if (!loginVar) {
        switchMyHistory.fadeOut();
        switchToday.fadeOut();
        switchProfile.fadeOut();
        switchLogin.text("Login");
        alreadyEatenEnergy = 0;
        todayTbodyEaten.empty();
        profileSelectMonth.prop('selectedIndex', 0);
        profileSelectYear.prop('selectedIndex', 0);
        profileSelectDay.prop('selectedIndex', 0);
        $.get("http://localhost:8091/DietManager/rest/index/remove/key?key=" + key, function (result) {
            key = 0;
        })
    } else {
        //switchMyHistory.fadeIn();
        switchToday.fadeIn();
        switchProfile.fadeIn();
        $.getJSON("http://localhost:8091/DietManager/rest/index/user?key=" + key, function (result) {
            if (result.weight === 0) {
                weight = 75;
                profileWeightInput.val(weight);
            } else {
                weight = result.weight;
                profileWeightInput.val(weight);
            }
            if (result.height === 0) {
                height = 175;
                profileHeightInput.val(height);
            } else {
                height = result.height;
                profileWeightInput.val(height);
            }
            if (result.birthDate != null) {
                var split = result.birthDate.split("-");
                birthdate = new Date();
                birthdate.setUTCFullYear(split[0]);
                birthdate.setUTCMonth(split[1] - 1);
                birthdate.setUTCDate(split[2]);
                profileSelectYear.val(split[0]);
                profileSelectMonth.prop('selectedIndex', birthdate.getMonth());
                profileSelectDay.prop('selectedIndex', birthdate.getDate() - 1);
            }
            if (result.sex) {
                gender = true;
                profileButtonFemale.removeClass("profile_button_gender_not");
                profileButtonMale.addClass("profile_button_gender_not");
            } else {
                gend$er = false;
                profileButtonMale.removeClass("profile_button_gender_not");
                profileButtonFemale.addClass("profile_button_gender_not");
            }
        });
        $.get("http://localhost:8091/DietManager/rest/index/daily?key=" + key, function (result) {
            if (result === -2) {
                if (!page == SwitchEnum.PROFILE) {
                    switchPage(SwitchEnum.PROFILE, sectionProfile);
                    alert("Set your Data first");
                }
            } else {
                dailyEnergy = result;
            }
        });
        $.getJSON("http://localhost:8091/DietManager/rest/index/today?key=" + key, function (result) {
            $.each(result, function (i, field) {
                if (field.food.foodID === 1) {
                    alreadyEatenEnergy += field.amount;
                    todayTbodyEaten.append("<tr id='tline" + field.eatenFoodID + "'><th>Calories</th><th>" + field.amount + " kcal</th><th>-</th><th><img onclick='today_button_clicked(" + field.eatenFoodID + "," + field.food.foodID + "," + field.amount + ")' class='today_img_trash' src='trash.png'/></th></tr>");
                }
            });
        });
    }
}

var switchPage = function (p, section) {
    switch (page) {
        case SwitchEnum.HOME:
            sectionHome.remove();
            break;
        case SwitchEnum.LOGIN:
            loginEmailInput.val("");
            loginPasswordInput.val("");
            loginUsernameInput.val("");
            sectionLogin.remove();
            break;
        case SwitchEnum.TODAY:
            sectionToday.remove();
            break;
        case SwitchEnum.PROFILE:
            setProfileDate();
            sectionProfile.remove();
            break;
    }
    switch (p) {
        case SwitchEnum.HOME:
            break;
        case SwitchEnum.LOGIN:
            buttonCreateAccount.click(function () {
                loginEmailGroup.fadeIn();
                buttonLogin.fadeOut(0);
                buttonToLogin.fadeIn();
                if (register) {
                    if (!validateEmail(loginEmailInput.val())) {
                        alert("Email Address not possible");
                    } else if (!validateUsername(loginUsernameInput.val())) {
                        alert("Username not possible");
                    }
                    else {
                        var tarid = 1;
                        $.get("http://localhost:8091/DietManager/rest/index/add/user?username=" + loginUsernameInput.val() + "&password=" + loginPasswordInput.val() + "&email=" + loginEmailInput.val() + "&sex=m&targetId=" + tarid, function (result) {
                            if (result == 0) {
                                alert("Email or Username is not available!");
                            } else {
                                key = result;
                                switchLogin.text("Logout " + loginUsernameInput.val());
                                switchPage(SwitchEnum.PROFILE, sectionProfile);
                                loginChange(true);
                            }
                        });
                    }
                }
                register = true;
            });
            buttonLogin.click(function () {
                $.get("http://localhost:8091/DietManager/rest/index/login?username=" + loginUsernameInput.val() + "&password=" + loginPasswordInput.val(), function (result) {
                    if (result == 0) {
                        alert("wrong password");
                    }
                    else if (result == 1) {
                        loginEmailGroup.fadeIn();
                        buttonLogin.fadeOut(0);
                        buttonToLogin.fadeIn();
                        register = true;
                        alert("Username does not exist");
                    } else {
                        key = result;
                        switchLogin.text("Logout " + loginUsernameInput.val())
                        loginChange(true);
                        switchPage(SwitchEnum.HOME, sectionHome);
                    }

                });
            });
            buttonToLogin.click(function () {
                loginEmailGroup.fadeOut();
                buttonLogin.fadeIn();
                buttonToLogin.fadeOut();
                register = false;
            });
            break;
        case SwitchEnum.TODAY:
            todayButtonCalories.click(function () {
                if (todayButtonCalories.hasClass("profile_button_gender_not")) {
                    todayButtonFood.addClass("profile_button_gender_not");
                    todayButtonCalories.removeClass("profile_button_gender_not");
                    todayButtonCalories.text("ADD");
                    todayButtonFood.text("Add Food");
                    todayButtonCalories.before(todayDivCalories);
                } else {
                    var plusValue = todayInputCalories.val();
                    alreadyEatenEnergy = parseInt(plusValue) + alreadyEatenEnergy;
                    $.post("http://localhost:8091/DietManager/rest/index/add/eatenFood?key=" + key + "&foodId=1&amount=" + plusValue, function (result) {
                        todayInputCalories.val("");
                        todayTbodyEaten.append("<tr id='tline" + result + "'><th>Calories</th><th>" + plusValue + " kcal</th><th>-</th><th><img onclick='today_button_clicked(" + result + ",1," + plusValue + ")' class='today_img_trash' src='trash.png'/></th></tr>");
                        drawChart();
                        alert("calories successfully added");
                    });
                }
            });
            todayButtonFood.click(function () {
                if (todayButtonFood.hasClass("profile_button_gender_not")) {
                    todayButtonCalories.addClass("profile_button_gender_not");
                    todayButtonFood.removeClass("profile_button_gender_not");
                    todayButtonFood.text("ADD");
                    todayButtonCalories.text("Add Calories");
                    todayDivCalories.remove();
                } else {

                }
            });
            break;
        case SwitchEnum.PROFILE:
            profileButtonMale.click(function () {
                profileButtonMale.removeClass("profile_button_gender_not");
                profileButtonFemale.addClass("profile_button_gender_not");
            });
            profileButtonFemale.click(function () {
                profileButtonFemale.removeClass("profile_button_gender_not");
                profileButtonMale.addClass("profile_button_gender_not");
            });
            profileButtonSave.click(function () {
                weight = profileWeightInput.val();
                height = profileHeightInput.val();
                birthdate = new Date();
                birthdate.setUTCFullYear(profileSelectYear.val());
                birthdate.setUTCMonth(profileSelectMonth.prop('selectedIndex'));
                birthdate.setUTCDate(profileSelectDay.prop('selectedIndex') + 1);
                if (profileButtonFemale.hasClass("profile_button_gender_not"))
                    gender = false;
                else
                    gender = true;
                $.post("http://localhost:8091/DietManager/rest/index/profile?key=" + key + "&height=" + height + "&weight=" + weight + "&date=" + getSqlDate(birthdate) + "&gender=" + gender, function () {
                    alert("Data is saved!");
                    $.get("http://localhost:8091/DietManager/rest/index/daily?key=" + key, function (result) {
                        if (result === -2) {
                            if (!page == SwitchEnum.PROFILE) {
                                switchPage(SwitchEnum.PROFILE, sectionProfile);
                                alert("Set your Data first");
                            }
                        } else {
                            dailyEnergy = result;
                            drawChart();
                        }
                    });
                });
            });
            setProfileDate();
            break;
    }
    contentinner.append(section);
    if (p == SwitchEnum.TODAY) {
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);
    }
    page = p;

    loginEmailGroup.fadeOut(0);
    buttonToLogin.fadeOut(0);
    buttonLogin.fadeIn(0);
    register = false;
}

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validateUsername(username) {
    if (username.length < 4 || username.length > 16)
        return false;
    return true;
}

function setProfileDate() {
    if (gender) {
        profileButtonFemale.removeClass("profile_button_gender_not");
        profileButtonMale.addClass("profile_button_gender_not");
    } else {
        profileButtonFemale.addClass("profile_button_gender_not");
        profileButtonMale.removeClass("profile_button_gender_not");
    }
    profileWeightInput.val(weight);
    profileHeightInput.val(height);
}

function getSqlDate(date) {
    date = date.getUTCFullYear() + '-' +
        ('00' + (date.getUTCMonth() + 1)).slice(-2) + '-' +
        ('00' + date.getUTCDate()).slice(-2);
    return date;
}

function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Entry', 'Amount'],
        ['To Eat', dailyEnergy - alreadyEatenEnergy > 0 ? dailyEnergy - alreadyEatenEnergy : 0],
        ['Eaten', alreadyEatenEnergy]
    ]);
    var options = {
        title: 'My Energy balance (kCal)'
    };


    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);

}

function today_button_clicked(eatenFoodID, foodID, amount) {
    $.get("http://localhost:8091/DietManager/rest/index/remove/eatenFood?key=" + key + "&eatenFoodId=" + eatenFoodID, function (result) {
        var element = $("#tline" + eatenFoodID);
        element.remove();
        if (foodID === 1)
            alreadyEatenEnergy -= amount;
        drawChart();
        alert("successfully removed");
    })
}
