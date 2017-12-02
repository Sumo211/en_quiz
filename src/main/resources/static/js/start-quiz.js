//Globle varivable
var urlGetDataSurvey = "/test"
var survey;
var dataSurvey;

//Some properties for Survey
Survey.Survey.cssType = "bootstrap";
Survey.defaultBootstrapCss.navigationButton = "btn btn-green";
// Survey.surveyLocalization.locales["my"] = {
//     timerMin: "phút",
//     timerSec: "giây",
//     completeText: "Hoàn thành",
//     timerLimitSurvey: "Bạn đã tốn {0} trong tổng số {1}.",
// };

$(document).ready(function() {

    //Get data for survey
    dataSurvey = getDataSurvey();

    //Create object survey
    survey = new Survey.Model(dataSurvey);

    //Setup some child properties for survey
    // survey.showProgressBar = "bottom";
    survey.showTimerPanel = "top";
    survey.completedHtml = "Bạn đã hoàn thành bài thi.";
    survey.showTimerPanelMode = "survey";

    //Start Survey
    $(".btn-start").click(function() {
        $("#divStartQuiz").css("display", "none");

        $("#surveyElement").Survey({
            model: survey
        });
    });

    //On Survey complete
    survey.onComplete.add(function(result) {
        $(".wrap-survey").hide();

        var data = new Object();
        // data.result = result.data;
        data["result"] = converResultData(result.data);
        data["userId"] = 10;

        data["userId"] = 1;
        $("#surveyResult").html("Result: " + JSON.stringify(data));

        console.log("Result posst: ", data);
        postResult(data);
    });

});

function converResultData(data) {
    var quizResults = new Array();

    for (var key in data) {
        if (data.hasOwnProperty(key)) {
            console.log("Key: " + key);
            var question = new Object();
            question["questionId"] = key;

            let values = data[key];



            if (typeof values === "string") {
                question["answerIds"] = values;
            } else if (typeof values === "object") {
                console.log("is Array: " + values.length);
                question["answerIds"] = values.join();
            }
            quizResults.push(question);
        }
    }
    console.log("11111: ", quizResults);
    return quizResults;
}

function getDataSurvey() {
    var json;
    $.ajaxSetup({ async: false });
    $.get(urlGetDataSurvey, function(data, status) {
        json = data;
    });
    return json;
}

function postResult(data) {
    $.ajax({
        "type": "POST",
        "url": "/quiz-result",
        "dataType": "json",
        "contentType": "application/json; charset=utf-8",
        "data": JSON.stringify(data), // serializes the form's elements.
        "success": function(dataReturn) {
            console.log("data: ", dataReturn);
        },
        "error": function() {
            console.log("Error...");
        }
    });
}