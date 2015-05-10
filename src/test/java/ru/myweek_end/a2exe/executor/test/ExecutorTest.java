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

package ru.myweek_end.a2exe.executor.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import ru.myweek_end.a2exe.executor.Executor;

/**
 * Класс тестирования класса запуска и останавки apache2.
 * 
 * @author <a href="https://myweek-end.ru/">Моя неделя завершилась</a>
 * @author <a href="mailto:drum@pisem.net">Алексей Кляузер</a>
 * @since 0.0.1.2
 * @version 0.0.1.2
 */
public class ExecutorTest {

  private Executor executor;

  /**
   * Стандартная директория конфигураций модулей apache2
   * 
   * @since 0.0.1.2
   */
  private Path standartModsEnabledDir;

  /**
   * Альтернативная директория конфигураций модулей apache2
   * 
   * @since 0.0.1.2
   */
  private Path altModsEnabledDir;

  /**
   * Стандартное имя сервера apache2
   * 
   * @since 0.0.1.2
   */
  private String standartServerName = "localhost";

  /**
   * Альтернативное имя сервера apache2
   * 
   * @since 0.0.1.2
   */
  private String altServerName = "example.org";

  /**
   * Стандартный файл хранения идентификатора процесса apache2
   * 
   * @since 0.0.1.2
   */
  private File standartPidFile;

  /**
   * Альтернативный файл хранения идентификатора процесса apache2 при установке
   * root
   * 
   * @since 0.0.1.2
   */
  private File altPidFile0;

  /**
   * Альтернативный файл хранения идентификатора процесса apache2 при установке
   * serviceRoot
   * 
   * @since 0.0.1.2
   */
  private File altPidFile1;

  /**
   * Альтернативный файл хранения идентификатора процесса apache2
   * 
   * @since 0.0.1.2
   */
  private File altPidFile2;

  /**
   * Стандартный файл журнала apache2
   * 
   * @since 0.0.1.2
   */
  private File standartCustomLog;

  /**
   * Альтернативный файл журнала apache2 при установке root
   * 
   * @since 0.0.1.2
   */
  private File altCustomLog0;

  /**
   * Альтернативный файл журнала apache2 при установке serviceRoot
   * 
   * @since 0.0.1.2
   */
  private File altCustomLog1;

  /**
   * Альтернативный файл журнала apache2
   * 
   * @since 0.0.1.2
   */
  private File altCustomLog2;

  /**
   * Стандартный файл журнала ошибок apache2
   * 
   * @since 0.0.1.2
   */
  private File standartErrorLog;

  /**
   * Альтернативный файл журнала ошибок apache2 при установке root
   * 
   * @since 0.0.1.2
   */
  private File altErrorLog0;

  /**
   * Альтернативный файл журнала ошибок apache2 при установке serviceRoot
   * 
   * @since 0.0.1.2
   */
  private File altErrorLog1;

  /**
   * Альтернативный файл журнала ошибок apache2
   * 
   * @since 0.0.1.2
   */
  private File altErrorLog2;

  /**
   * Стандартный файл конфигурации apache2
   * 
   * @since 0.0.1.2
   */
  private File standartConfFile;

  /**
   * Альтернативный файл конфигурации apache2 при установке root
   * 
   * @since 0.0.1.2
   */
  private File altConfFile0;

  /**
   * Альтернативный файл конфигурации apache2 при установке serviceRoot
   * 
   * @since 0.0.1.2
   */
  private File altConfFile1;

  /**
   * Альтернативный файл конфигурации apache2
   * 
   * @since 0.0.1.2
   */
  private File altConfFile2;

  /**
   * Стандартная директория служебных файлов apache2
   * 
   * @since 0.0.1.2
   */
  private Path standartServiceRoot;

  /**
   * Альтернативная директория служебных файлов apache2 при установке root
   * 
   * @since 0.0.1.2
   */
  private Path altServiceRoot0;

  /**
   * Альтернативная директория служебных файлов apache2
   * 
   * @since 0.0.1.2
   */
  private Path altServiceRoot1;

  /**
   * Стандартная директория документов apache2
   * 
   * @since 0.0.1.2
   */
  private Path standartDocumentRoot;

  /**
   * Альтернативная директория документов apache2 при установке root
   * 
   * @since 0.0.1.2
   */
  private Path altDocumentRoot0;

  /**
   * Альтернативная директория документов apache2
   * 
   * @since 0.0.1.2
   */
  private Path altDocumentRoot1;

  /**
   * Стандартная корневая директория сайта
   * 
   * @since 0.0.1.2
   */
  private Path standartRoot;

  /**
   * Альтернативная корневая директория сайта
   * 
   * @since 0.0.1.2
   */
  private Path altRoot;

  /**
   * Создание компонента для тестирования
   * 
   * @throws IOException
   *           При проблемах с файлами
   * @since 0.0.1.2
   */
  @Before
  public void setUp() throws IOException {
    this.executor = new Executor(new File("./target/a2exetest")
        .getCanonicalFile().toPath());
    this.standartModsEnabledDir = new File("/etc/apache2/mods-enabled")
        .toPath();
    this.altModsEnabledDir = new File("/etc/apache2/mods-enabled").toPath();
    this.standartPidFile = new File("./target/a2exetest/conf/apache2.pid")
        .getCanonicalFile();
    this.altPidFile0 = new File("./target/testa2exe/conf/apache2.pid")
        .getCanonicalFile();
    this.altPidFile1 = new File("./target/a2exetest/serv/apache2.pid")
        .getCanonicalFile();
    this.altPidFile2 = new File("./target/a2exetest/conf/httpd.pid")
        .getCanonicalFile();
    this.standartCustomLog = new File("./target/a2exetest/conf/access.log")
        .getCanonicalFile();
    this.altCustomLog0 = new File("./target/testa2exe/conf/access.log")
        .getCanonicalFile();
    this.altCustomLog1 = new File("./target/a2exetest/serv/access.log")
        .getCanonicalFile();
    this.altCustomLog2 = new File("./target/a2exetest/conf/httpd.log")
        .getCanonicalFile();
    this.standartErrorLog = new File("./target/a2exetest/conf/error.log")
        .getCanonicalFile();
    this.altErrorLog0 = new File("./target/testa2exe/conf/error.log")
        .getCanonicalFile();
    this.altErrorLog1 = new File("./target/a2exetest/serv/error.log")
        .getCanonicalFile();
    this.altErrorLog2 = new File("./target/a2exetest/conf/err.log")
        .getCanonicalFile();
    this.standartConfFile = new File("./target/a2exetest/conf/apache2.conf")
        .getCanonicalFile();
    this.altConfFile0 = new File("./target/testa2exe/conf/apache2.conf")
        .getCanonicalFile();
    this.altConfFile1 = new File("./target/a2exetest/serv/apache2.conf")
        .getCanonicalFile();
    this.altConfFile2 = new File("./target/a2exetest/conf/httpd.conf")
        .getCanonicalFile();
    this.standartServiceRoot = new File("./target/a2exetest/conf")
        .getCanonicalFile().toPath();
    this.altServiceRoot0 = new File("./target/testa2exe/conf")
        .getCanonicalFile().toPath();
    this.altServiceRoot1 = new File("./target/a2exetest/serv")
        .getCanonicalFile().toPath();
    this.standartDocumentRoot = new File("./target/a2exetest/www")
        .getCanonicalFile().toPath();
    this.altDocumentRoot0 = new File("./target/testa2exe/www")
        .getCanonicalFile().toPath();
    this.altDocumentRoot1 = new File("./target/a2exetest/docs")
        .getCanonicalFile().toPath();
    this.standartRoot = new File("./target/a2exetest").getCanonicalFile()
        .toPath();
    this.altRoot = new File("./target/testa2exe").getCanonicalFile().toPath();
  }

  /**
   * Test method for
   * {@link ru.myweek_end.a2exe.executor.Executor#getModsEnabledDir()}.
   * 
   * @since 0.0.1.2
   */
  @Test
  public void testGetModsEnabledDir() {
    assertEquals(this.standartModsEnabledDir, this.executor.getModsEnabledDir());
    executor.setModsEnabledDir(this.altModsEnabledDir);
    assertEquals(this.altModsEnabledDir, this.executor.getModsEnabledDir());
  }

  /**
   * Test method for {@link ru.myweek_end.a2exe.executor.Executor#getPort()}.
   * 
   * @since 0.0.1.2
   */
  @Test
  public void testGetPort() {
    int port = this.executor.getPort();
    assertTrue("8000 ≤ " + " < 9000", ((8000 <= port) && (9000 > port)));
    assertEquals(port, this.executor.getPort());
    executor.setPort(10984);
    assertEquals(10984, this.executor.getPort());
  }

  /**
   * Test method for
   * {@link ru.myweek_end.a2exe.executor.Executor#getServerName()}.
   * 
   * @since 0.0.1.2
   */
  @Test
  public void testGetServerName() {
    assertEquals(
        this.standartServerName + ":"
            + Integer.toString(this.executor.getPort()),
        this.executor.getServerName());
    executor.setServerName(this.altServerName);
    assertEquals(this.altServerName, this.executor.getServerName());
  }

  /**
   * Test method for {@link ru.myweek_end.a2exe.executor.Executor#getPidFile()}.
   * 
   * @since 0.0.1.2
   */
  @Test
  public void testGetPidFile() {
    assertEquals(this.standartPidFile, this.executor.getPidFile());
    executor.setRoot(this.altRoot);
    assertEquals(this.altPidFile0, this.executor.getPidFile());
    executor.setServiceRoot(this.altServiceRoot1);
    assertEquals(this.altPidFile1, this.executor.getPidFile());
    executor.setPidFile(this.altPidFile2);
    assertEquals(this.altPidFile2, this.executor.getPidFile());
  }

  /**
   * Test method for
   * {@link ru.myweek_end.a2exe.executor.Executor#getCustomLog()}.
   * 
   * @since 0.0.1.2
   */
  @Test
  public void testGetCustomLog() {
    assertEquals(this.standartCustomLog, this.executor.getCustomLog());
    executor.setRoot(this.altRoot);
    assertEquals(this.altCustomLog0, this.executor.getCustomLog());
    executor.setServiceRoot(this.altServiceRoot1);
    assertEquals(this.altCustomLog1, this.executor.getCustomLog());
    executor.setCustomLog(this.altCustomLog2);
    assertEquals(this.altCustomLog2, this.executor.getCustomLog());
  }

  /**
   * Test method for {@link ru.myweek_end.a2exe.executor.Executor#getErrorLog()}
   * .
   * 
   * @since 0.0.1.2
   */
  @Test
  public void testGetErrorLog() {
    assertEquals(this.standartErrorLog, this.executor.getErrorLog());
    executor.setRoot(this.altRoot);
    assertEquals(this.altErrorLog0, this.executor.getErrorLog());
    executor.setServiceRoot(this.altServiceRoot1);
    assertEquals(this.altErrorLog1, this.executor.getErrorLog());
    executor.setErrorLog(this.altErrorLog2);
    assertEquals(this.altErrorLog2, this.executor.getErrorLog());
  }

  /**
   * Test method for {@link ru.myweek_end.a2exe.executor.Executor#getConfFile()}
   * .
   * 
   * @since 0.0.1.2
   */
  @Test
  public void testGetConfFile() {
    assertEquals(this.standartConfFile, this.executor.getConfFile());
    executor.setRoot(this.altRoot);
    assertEquals(this.altConfFile0, this.executor.getConfFile());
    executor.setServiceRoot(this.altServiceRoot1);
    assertEquals(this.altConfFile1, this.executor.getConfFile());
    executor.setConfFile(this.altConfFile2);
    assertEquals(this.altConfFile2, this.executor.getConfFile());
  }

  /**
   * Test method for
   * {@link ru.myweek_end.a2exe.executor.Executor#getServiceRoot()}.
   * 
   * @since 0.0.1.2
   */
  @Test
  public void testGetServiceRoot() {
    assertEquals(this.standartServiceRoot, this.executor.getServiceRoot());
    executor.setRoot(this.altRoot);
    assertEquals(this.altServiceRoot0, this.executor.getServiceRoot());
    executor.setServiceRoot(this.altServiceRoot1);
    assertEquals(this.altServiceRoot1, this.executor.getServiceRoot());
  }

  /**
   * Test method for
   * {@link ru.myweek_end.a2exe.executor.Executor#getDocumentRoot()}.
   * 
   * @since 0.0.1.2
   */
  @Test
  public void testGetDocumentRoot() {
    assertEquals(this.standartDocumentRoot, this.executor.getDocumentRoot());
    executor.setRoot(this.altRoot);
    assertEquals(this.altDocumentRoot0, this.executor.getDocumentRoot());
    executor.setDocumentRoot(this.altDocumentRoot1);
    assertEquals(this.altDocumentRoot1, this.executor.getDocumentRoot());
  }

  /**
   * Test method for {@link ru.myweek_end.a2exe.executor.Executor#getRoot()}.
   * 
   * @since 0.0.1.2
   */
  @Test
  public void testGetRoot() {
    assertEquals(this.standartRoot, this.executor.getRoot());
    executor.setRoot(this.altRoot);
    assertEquals(this.altRoot, this.executor.getRoot());
  }

}
