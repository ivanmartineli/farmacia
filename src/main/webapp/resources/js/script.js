
// Efeito para não fechar a dialog quando os campos obrigatorio não forem preenchidos
function verificarCampos(xhr, status, args, dlg, tb) {
    if (args.validationFailed || !args.loggedIn) {
        PF(dlg).jq.effect("shake", {times: 5}, 100);
    } else {
        PF(dlg).hide();
        PF(tb).clearFilters();
    }
}
