
//Globle varivable
var urlGetDataSurvey = "/test"
var survey;
var dataSurvey;

//Some properties for Survey
Survey.Survey.cssType = "bootstrap";
Survey.defaultBootstrapCss.navigationButton = "btn btn-green";
Survey.surveyLocalization.locales["my"] = {
    timerMin: "phút",
    timerSec: "giây",
    completeText: "Hoàn thành",
    timerLimitSurvey: "Bạn đã tốn {0} trong tổng số {1}.",
};


$(document).ready(function () {

    //Get data for survey
    dataSurvey = getDataSurvey();

    //Create object survey
    survey = new Survey.Model(dataSurvey);

    //Setup some child properties for survey
    survey.showProgressBar = "bottom";
    survey.showTimerPanel = "top";
    survey.completedHtml = "Bạn đã hoàn thành bài thi.";
    survey.showTimerPanelMode = "survey";
    survey.locale = "my";

    //Start Survey
    $(".btn-start").click(function () {
        $("#divStartQuiz").css("display", "none");

        $("#surveyElement").Survey({
            model: survey
        });
    });

    //On Survey complete
    survey.onComplete.add(function (result) {
        $(".wrap-survey").hide();
        $("#surveyResult").html("Result: "+JSON.stringify(result.data));
    });

});

function getDataSurvey() {
    var json;
    $.ajaxSetup({ async: false });
    $.get(urlGetDataSurvey, function (data, status) {
        json = data;
    });
    return json;
}

