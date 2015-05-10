/*
 * Copyright © 2015 "Алексей Кляузер <drum@pisem.net>" Все права защищены.
 */

/*
 * This file is part of java_a2exe.
 *
 * java_a2exe is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * java_a2exe is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with java_a2exe.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Этот файл — часть java_a2exe.
 *
 * java_a2exe - свободная программа: вы можете перераспространять ее и/или
 * изменять ее на условиях Афферо Стандартной общественной лицензии GNU в
 * том виде, в каком она была опубликована Фондом свободного программного
 * обеспечения; либо версии 3 лицензии, либо (по вашему выбору) любой более
 * поздней версии.
 *
 * java_a2exe распространяется в надежде, что она будет полезной, но БЕЗО
 * ВСЯКИХ ГАРАНТИЙ; даже без неявной гарантии ТОВАРНОГО ВИДА или ПРИГОДНОСТИ
 * ДЛЯ ОПРЕДЕЛЕННЫХ ЦЕЛЕЙ. Подробнее см. в Афферо Стандартной общественной
 * лицензии GNU.
 *
 * Вы должны были получить копию Афферо Стандартной общественной лицензии GNU
 * вместе с этой программой. Если это не так, см.
 * <http://www.gnu.org/licenses/>.
 */

package ru.myweek_end.a2exe.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.Random;

/**
 * Класс запускающий и останавливающий apache2.
 * 
 * @author <a href="https://myweek-end.ru/">Моя неделя завершилась</a>
 * @author <a href="mailto:drum@pisem.net">Алексей Кляузер</a>
 * @since 0.0.1.2
 * @version 0.0.1.2
 */
public class Executor {

  /**
   * Минимальный номер порта который будет слушать apache2 по умолчанию.
   * 
   * @since 0.0.1.2
   */
  private static final int DEF_MAX_PORT = 9000;

  /**
   * Максимальный номер порта который будет слушать apache2 по умолчанию.
   * 
   * @since 0.0.1.2
   */
  private static final int DEF_MIN_PORT = 8000;

  /**
   * Директория конфигураций модулей apache2.
   * 
   * @since 0.0.1.2
   */
  private Path modsEnabledDir;

  /**
   * Порт который будет слушать apache2.
   * 
   * @since 0.0.1.2
   */
  private int port = 0;

  /**
   * Минимальный номер порта который будет слушать apache2.
   * 
   * @since 0.0.1.2
   */
  private int minPort = DEF_MIN_PORT;

  /**
   * Максимальный номер порта который будет слушать apache2.
   * 
   * @since 0.0.1.2
   */
  private int maxPort = DEF_MAX_PORT;

  /**
   * Имя сервера apache2.
   * 
   * @since 0.0.1.2
   */
  private String serverName;

  /**
   * Файл хранения идентификатора процесса apache2.
   * 
   * @since 0.0.1.2
   */
  private File pidFile;

  /**
   * Файл журнала apache2.
   * 
   * @since 0.0.1.2
   */
  private File customLog;

  /**
   * Файл журнала ошибок apache2.
   * 
   * @since 0.0.1.2
   */
  private File errorLog;

  /**
   * Файл конфигурации apache2.
   * 
   * @since 0.0.1.2
   */
  private File confFile;

  /**
   * Директория служебных файлов apache2.
   * 
   * @since 0.0.1.2
   */
  private Path serviceRoot;

  /**
   * Директория документов apache2.
   * 
   * @since 0.0.1.2
   */
  private Path documentRoot;

  /**
   * Корневая директория сайта.
   * 
   * @since 0.0.1.2
   */
  private Path root;

  /**
   * Конструктор.
   * 
   * @since 0.0.1.2
   * @param root
   *          Корневая директория сайта
   */
  public Executor(final Path root) {
    this.root = root;
  }

  /**
   * Получить директорию конфигураций модулей apache2.
   * 
   * @since 0.0.1.2
   * @return Директория конфигураций модулей apache2
   */
  public final Path getModsEnabledDir() {
    if (this.modsEnabledDir == null) {
      return new File("/etc/apache2/mods-enabled").toPath();
    } else {
      return this.modsEnabledDir;
    }
  }

  /**
   * Установить директорию конфигураций модулей apache2.
   * 
   * @since 0.0.1.2
   * @param value
   *          Директория конфигураций модулей apache2
   */
  public final void setModsEnabledDir(final Path value) {
    this.modsEnabledDir = value;
  }

  /**
   * Получить порт который будет слушать apache2.
   * 
   * @since 0.0.1.2
   * @return Порт который будет слушать apache2
   */
  public final int getPort() {
    if (this.port == 0) {
      Random random = new Random(new Date().getTime());
      this.port = this.minPort + random.nextInt(this.maxPort - this.minPort);
    }
    return this.port;
  }

  /**
   * Установить порт который будет слушать apache2.
   * 
   * @since 0.0.1.2
   * @param value
   *          Порт который будет слушать apache2
   */
  public final void setPort(final int value) {
    this.port = value;
  }

  /**
   * Получить имя сервера apache2.
   * 
   * @since 0.0.1.2
   * @return Имя сервера apache2
   */
  public final String getServerName() {
    if (this.serverName == null) {
      return "localhost:" + Integer.toString(this.getPort());
    } else {
      return this.serverName;
    }
  }

  /**
   * Установить имя сервера apache2.
   * 
   * @since 0.0.1.2
   * @param value
   *          Имя сервера apache2
   */
  public final void setServerName(final String value) {
    this.serverName = value;
  }

  /**
   * Получить файл хранения идентификатора процесса apache2.
   * 
   * @since 0.0.1.2
   * @return Файл хранения идентификатора процесса apache2
   */
  public final File getPidFile() {
    if (this.pidFile == null) {
      return this.getServiceRoot().resolve("apache2.pid").toFile();
    } else {
      return this.pidFile;
    }
  }

  /**
   * Установить файл хранения идентификатора процесса apache2.
   * 
   * @since 0.0.1.2
   * @param value
   *          Файл хранения идентификатора процесса apache2
   */
  public final void setPidFile(final File value) {
    this.pidFile = value;
  }

  /**
   * Получить файл журнала apache2.
   * 
   * @since 0.0.1.2
   * @return Файл журнала apache2
   */
  public final File getCustomLog() {
    if (this.customLog == null) {
      return this.getServiceRoot().resolve("access.log").toFile();
    } else {
      return this.customLog;
    }
  }

  /**
   * Установить файл журнала apache2.
   * 
   * @since 0.0.1.2
   * @param value
   *          Файл журнала apache2
   */
  public final void setCustomLog(final File value) {
    this.customLog = value;
  }

  /**
   * Получить файл журнала ошибок apache2.
   * 
   * @since 0.0.1.2
   * @return Файл журнала ошибок apache2
   */
  public final File getErrorLog() {
    if (this.errorLog == null) {
      return this.getServiceRoot().resolve("error.log").toFile();
    } else {
      return this.errorLog;
    }
  }

  /**
   * Установить файл журнала ошибок apache2.
   * 
   * @since 0.0.1.2
   * @param value
   *          Файл журнала ошибок apache2
   */
  public final void setErrorLog(final File value) {
    this.errorLog = value;
  }

  /**
   * Получить файл конфигурации apache2.
   * 
   * @since 0.0.1.2
   * @return Файл конфигурации apache2
   */
  public final File getConfFile() {
    if (this.confFile == null) {
      return this.getServiceRoot().resolve("apache2.conf").toFile();
    } else {
      return this.confFile;
    }
  }

  /**
   * Установить файл конфигурации apache2.
   * 
   * @since 0.0.1.2
   * @param value
   *          Файл конфигурации apache2
   */
  public final void setConfFile(final File value) {
    this.confFile = value;
  }

  /**
   * Получить директорию служебных файлов apache2.
   * 
   * @since 0.0.1.2
   * @return Директория служебных файлов apache2
   */
  public final Path getServiceRoot() {
    if (this.serviceRoot == null) {
      return this.getRoot().resolve("conf");
    } else {
      return this.serviceRoot;
    }
  }

  /**
   * Установить директорию служебных файлов apache2.
   * 
   * @since 0.0.1.2
   * @param value
   *          Директория служебных файлов apache2
   */
  public final void setServiceRoot(final Path value) {
    this.serviceRoot = value;
  }

  /**
   * Получить директорию документов apache2.
   * 
   * @since 0.0.1.2
   * @return Директория документов apache2
   */
  public final Path getDocumentRoot() {
    if (this.documentRoot == null) {
      return this.getRoot().resolve("www");
    } else {
      return this.documentRoot;
    }
  }

  /**
   * Установить директорию документов apache2.
   * 
   * @since 0.0.1.2
   * @param value
   *          Директория документов apache2
   */
  public final void setDocumentRoot(final Path value) {
    this.documentRoot = value;
  }

  /**
   * Получить корневую директорию сайта.
   * 
   * @since 0.0.1.2
   * @return Корневая директория сайта
   */
  public final Path getRoot() {
    return this.root;
  }

  /**
   * Установить корневую директорию сайта.
   * 
   * @since 0.0.1.2
   * @param value
   *          Корневая директория сайта
   */
  public final void setRoot(final Path value) {
    this.root = value;
  }

  /**
   * Запуск копии веб сервера.
   * 
   * @throws IOException
   *           При проблемах с файловой системой
   * @throws InterruptedException
   *           При проблемах вызова apache2ctl
   * @throws ExecutorException
   *           При проблемах с запуском apache2
   */
  public final void start() throws IOException, InterruptedException,
      ExecutorException {
    File pidFile = this.getPidFile();
    if (pidFile.exists()) {
      this.stop();
    }
    File documentRoot = this.getDocumentRoot().toFile();
    if (!documentRoot.exists()) {
      documentRoot.mkdirs();
    }
    File serviceRoot = this.getServiceRoot().toFile();
    if (!serviceRoot.exists()) {
      serviceRoot.mkdirs();
    }
    File customLog = this.getCustomLog();
    if (customLog.exists()) {
      customLog.delete();
    }
    File errorLog = this.getErrorLog();
    if (errorLog.exists()) {
      errorLog.delete();
    }
    Path modsEnabled = this.getModsEnabledDir();
    File confFile = this.getConfFile();

    StringBuilder conf = new StringBuilder();
    conf.append("PidFile ");
    conf.append(pidFile.toString());
    conf.append("\r\n");
    conf.append("\r\n");
    conf.append("Include ");
    conf.append(modsEnabled.toString());
    conf.append("/*.load\r\n");
    conf.append("Include ");
    conf.append(modsEnabled.toString());
    conf.append("/*.conf\r\n");
    conf.append("\r\n");
    conf.append("Listen ");
    conf.append(Integer.toString(this.getPort()));
    conf.append("\r\n");
    conf.append("ServerName ");
    conf.append(this.getServerName());
    conf.append("\r\n");
    conf.append("\r\n");
    conf.append("LogFormat \"%h %l %u %t \\\"%r\\\" %>s %O \\\"%{Referer}i\\\" "
        + "\\\"%{User-Agent}i\\\"\" combined\r\n");
    conf.append("\r\n");
    conf.append("CustomLog ");
    conf.append(customLog.toString());
    conf.append(" combined\r\n");
    conf.append("ErrorLog  ");
    conf.append(errorLog.toString());
    conf.append("\r\n");
    conf.append("\r\n");
    conf.append("DocumentRoot ");
    conf.append(documentRoot.toString());
    conf.append("\r\n");
    conf.append("<Directory ");
    conf.append(documentRoot.toString());
    conf.append(">\r\n");
    conf.append("\tAllowoverride ALL\r\n");
    conf.append("\tOptions -Includes\r\n");
    conf.append("</Directory>\r\n");
    conf.append("\r\n");
    conf.append("AddHandler fcgid-script .php .php3 .php4 .php5 "
        + ".phtml\r\n\r\n");

    FileWriter confWriter = new FileWriter(confFile);
    try {
      confWriter.write(conf.toString());
    } finally {
      confWriter.close();
    }

    ProcessBuilder processBuilder = new ProcessBuilder("/usr/sbin/apache2ctl",
        "-f", confFile.toString(), "-k", "start");
    processBuilder.redirectErrorStream(true);
    Process process = processBuilder.start();
    InputStream stdout = process.getInputStream();
    InputStreamReader isrStdout = new InputStreamReader(stdout);
    BufferedReader brStdout = new BufferedReader(isrStdout);

    StringBuilder builder = new StringBuilder();
    String line = null;
    while ((line = brStdout.readLine()) != null) {
      builder.append(line);
      builder.append("\n");
    }
    process.waitFor();
    if (builder.length() != 0) {
      throw new ExecutorException(builder.toString());
    }
  }

  /**
   * Остановка копии веб сервера.
   * 
   * @throws IOException
   *           При проблемах с файловой системой
   * @throws InterruptedException
   *           При проблемах вызова apache2ctl
   * @throws ExecutorException
   *           При проблемах с запуском apache2
   */
  public final void stop() throws IOException, InterruptedException,
      ExecutorException {
    File confFile = this.getConfFile();

    ProcessBuilder processBuilder = new ProcessBuilder("/usr/sbin/apache2ctl",
        "-f", confFile.toString(), "-k", "stop");
    processBuilder.redirectErrorStream(true);
    Process process = processBuilder.start();
    InputStream stdout = process.getInputStream();
    InputStreamReader processStdout = new InputStreamReader(stdout);
    BufferedReader readerStdout = new BufferedReader(processStdout);

    StringBuilder builder = new StringBuilder();
    String line = null;
    while ((line = readerStdout.readLine()) != null) {
      builder.append(line);
      builder.append("\n");
    }
    process.waitFor();
    if (builder.length() != 0) {
      throw new ExecutorException(builder.toString());
    }
  }

}
