package api2.utils

import api2.ReajusteSalario

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Matcher
import java.util.regex.Pattern

class ReajusteSalarioUtils {
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    static LinkedHashMap formatResponse(ReajusteSalario reajusteSalario) {
        [
            id: reajusteSalario.id,
            funcionarioId: reajusteSalario.funcionarioId,
            dataReajuste: formatDataReajuste(reajusteSalario.dataReajuste),
            valorSalario: reajusteSalario.valorSalario
        ]
    }

    static Boolean isValidDataReajuste(String dataReajuste) {
        if (!dataReajuste) return false

        String regex = '^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$'
        Pattern pattern = Pattern.compile(regex)
        Matcher matcher = pattern.matcher(dataReajuste)

        return matcher.matches()
    }

    static String formatDataReajuste(LocalDate dataReajuste) { dataReajuste.format(FORMATTER) }

    static LocalDate parseDataReajuste(String dataReajuste) { LocalDate.parse(dataReajuste, FORMATTER) }
}
