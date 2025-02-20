package org.ngarcia.webapp.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {

   // Variable para almacenar la ruta absoluta a la carpeta logs
   private static String LOG_FOLDER;

   // Método para inicializar la ruta de la carpeta logs
   public static void setLogsFolder(String folderPath) {
      LOG_FOLDER = folderPath;
   }

   // Obtiene el nombre del archivo de log en formato "logYYMMDD.txt"
   private static String getLogFileName() {
      LocalDate today = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
      String dateStr = today.format(formatter);
      return "log" + dateStr + ".txt";
   }

   /**
    * Escribe un mensaje en el archivo de log.
    * Cada mensaje se anexa al final del archivo con un timestamp.
    *
    * @param message El mensaje a escribir en el log.
    */
   public static void log(String message) {
      if (LOG_FOLDER == null) {
         // Si no se ha inicializado LOG_FOLDER, se usa una ruta por defecto relativa
         LOG_FOLDER = "logs";
      }

      // Verificar que el directorio de logs exista, si no, crearlo
      File logDir = new File(LOG_FOLDER);
      if (!logDir.exists()) {
         logDir.mkdirs();
      }

      // Construir la ruta completa del archivo de log
      String fileName = getLogFileName();
      File logFile = new File(logDir, fileName);

      // Agregar timestamp al mensaje
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String timestamp = now.format(dtFormatter);
      String logEntry = "[" + timestamp + "] " + message;

      // Escribir el mensaje en el archivo (en modo append)
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))) {
         bw.write(logEntry);
         bw.newLine();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
