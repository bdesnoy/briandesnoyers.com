$(document).ready(function () {

            $("#patientWeightKg").on("input", function() {  // D8 (this)
                
                // Code to execute when the field is modified
                console.log("Field modified:", $(this).val());
                
                $pWeight = $(this).val()*1; // D8  
                if($pWeight>20)  // =IF(D8>20,(D8-20)+60,IF(D8>10,(D8-10)*2+40,D8*4))
                {
                     $("#maintenanceFluidRateMlHr").val((($pWeight-20)+60).toFixed(1)); //D10
                    
                }
                else if ($pWeight>10)
                    {
                        $("#maintenanceFluidRateMlHr").val((($pWeight-10)*2+40).toFixed(1)); //D10
                    }
                else
                    {
                        $("#maintenanceFluidRateMlHr").val(($pWeight*4).toFixed(1)); //D10
                    }
                
                $maintRate = $("#maintenanceFluidRateMlHr").val(); // D10
                
                $("#totalIVToBeAdmin").val(($maintRate*1.5).toFixed(1)); // D12   =D10*1.5
                
                $totalIVAdmin = $("#totalIVToBeAdmin").val(); // D12
                
                
                // bag columns
                $("#bag1Cell1Output").text(($totalIVAdmin*1).toFixed(1));
                $("#bag1Cell2Output").text(($totalIVAdmin *.75).toFixed(1));
                $("#bag1Cell3Output").text(($totalIVAdmin *.50).toFixed(1));
                $("#bag1Cell4Output").text(($totalIVAdmin *.25).toFixed(1));
                $("#bag1Cell5Output").text('0.0');
                
                
                $("#bag2Cell1Output").text('0.0');
                $("#bag2Cell2Output").text(($totalIVAdmin *.25).toFixed(1));
                $("#bag2Cell3Output").text(($totalIVAdmin *.50).toFixed(1));
                $("#bag2Cell4Output").text(($totalIVAdmin *.75).toFixed(1));
                $("#bag2Cell5Output").text(($totalIVAdmin*1).toFixed(1));
            });
});