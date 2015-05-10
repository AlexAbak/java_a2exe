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

package ru.myweek_end.a2exe.executor.itst;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

import ru.myweek_end.a2exe.executor.Executor;
import ru.myweek_end.a2exe.executor.ExecutorException;

/**
 * Класс тестирования запуска и останавки apache2.
 * 
 * @author <a href="https://myweek-end.ru/">Моя неделя завершилась</a>
 * @author <a href="mailto:drum@pisem.net">Алексей Кляузер</a>
 * @since 0.0.1.2
 * @version 0.0.1.2
 */
public class ExecutorIntegration {

  /**
   * Test method for {@link ru.myweek_end.a2exe.executor.Executor#start()}.
   * 
   * @throws IOException
   *           При проблемах с файловой системой
   * @throws InterruptedException
   *           При проблемах вызова apache2ctl
   * @throws ExecutorException
   *           При проблемах с запуском apache2
   */
  @Test
  public void testStart() throws IOException, InterruptedException,
      ExecutorException {
    Executor executor = new Executor(new File("./target/a2exetest")
        .getCanonicalFile().toPath());
    executor.start();
    try {
      String index = "<html>\n\t<head>\n\t\t<title>test</title>\n\t</head>\n\t<body>\n\t\t"
          + "<h1>test</h1>\n\t</body>\n</html>\n";
      FileWriter confWriter = new FileWriter(executor.getDocumentRoot()
          .resolve("index.html").toFile());
      try {
        confWriter.write(index);
      } finally {
        confWriter.close();
      }

      URL url = new URL("http://" + executor.getServerName());
      URLConnection connection = url.openConnection();
      connection.connect();
      StringBuilder builder = new StringBuilder();
      Reader reader = new InputStreamReader(connection.getInputStream());
      try {
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
          String line = null;
          while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
          }
        } finally {
          bufferedReader.close();
        }
      } finally {
        reader.close();
      }
      assertEquals(index, builder.toString());
    } finally {
      executor.stop();
    }
  }

}
