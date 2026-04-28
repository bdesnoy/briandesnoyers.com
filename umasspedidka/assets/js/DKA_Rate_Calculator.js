$(document).ready(function () {

            $("#totalIVPerHour").on("input", function() {
                // Code to execute when the field is modified
                console.log("Field modified:", $(this).val());

                $("#bag1Cell1Output").text(($(this).val()*1).toFixed(1));
                $("#bag1Cell2Output").text(($(this).val() *.75).toFixed(1));
                $("#bag1Cell3Output").text(($(this).val() *.50).toFixed(1));
                $("#bag1Cell4Output").text(($(this).val() *.25).toFixed(1));
                $("#bag1Cell5Output").text('0.0');
                
                
                $("#bag2Cell1Output").text('0.0');
                $("#bag2Cell2Output").text(($(this).val() *.25).toFixed(1));
                $("#bag2Cell3Output").text(($(this).val() *.50).toFixed(1));
                $("#bag2Cell4Output").text(($(this).val() *.75).toFixed(1));
                $("#bag2Cell5Output").text(($(this).val()*1).toFixed(1));
            });
});