package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import GUI.PlaybackWindow;

/**
 * Class to handle reading and writing of files.
 * 
 * @author Storm Behrens
 * @version 04/26/2021
 */
public class SaveHandler
{
  private static SaveHandler savedFileReader;
  private BufferedReader reader;
  private PrintWriter writer;
  private File file;

  /**
   * Default Constructor.
   * 
   * @throws IOException
   *           - the IOException
   */
  private SaveHandler() throws IOException
  {

    file = new File("SavedCalculations.txt");
    file.createNewFile();

  }

  /**
   * Close the reader or writer.
   * 
   * @param which
   *          Close READER or WRITER
   * @throws IOException
   *           the IOException
   */
  private void close(final int which) throws IOException
  {

    if (which == 0)
    {
      if (reader != null)
      {
        reader.close();
        reader = null;

      } // end if

    }
    else if (writer != null)
    {
      writer.close();
      writer = null;

    } // end if

  }

  /**
   * Static getInstance method for SaveHandler.
   * 
   * @return SaveHandler - the instance of the SaveHandler
   * @throws IOException
   */

  public static SaveHandler getInstance() throws IOException
  {
    if (savedFileReader == null)
    {
      savedFileReader = new SaveHandler();
    }
    return savedFileReader;
  }

  /**
   * Open the reader or writer.
   * 
   * @param which
   *          Open READER or WRITER
   * @return true if successful
   * @throws IOException
   *           theIOException
   */
  private boolean open(final int which) throws IOException
  {

    boolean canOpen = false;

    if (which == 0)
    {
      if (file.exists())
      {
        canOpen = true;
        reader = new BufferedReader(new FileReader(file));

      } // end if

    }
    else
    {
      writer = new PrintWriter(new FileWriter(file, true));

      if (file.canWrite())
      {
        canOpen = true;
      }

    } // end else

    return canOpen;

  }

  /**
   * Return the string read by the reader.
   * 
   * @return the string read by the reader
   * @throws IOException
   *           the IOException
   */
  private String readLine() throws IOException
  {

    String line = null;

    if (reader != null)
    {
      line = reader.readLine();
    }

    return line;
  }

  /**
   * Write a line to the file.
   * 
   * @param line
   *          the line to write
   */
  private void write(final String line)
  {

    if (writer != null)
    {
      writer.println(line);
    }
  }

  /**
   * reads the file and gives a hashmap of keys and values.
   * 
   * @return HashMap - the saved calculations
   * 
   * @throws IOException
   *           the IOException
   */
  public HashMap<String, String> readFile() throws IOException
  {

    String line = null;
    HashMap<String, String> saved = new HashMap<String, String>();

    if (open(0))
    {

      line = readLine();

      while (line != null)
      {

        String[] fields = line.split("\\|");
        String key = fields[0];
        String value = fields[1];
        saved.put(key, value);

        line = readLine();

      }
      close(0);

    }
    return saved;

  } // method readFiles

  /**
   * Adds a saved calculation and its key to the file.
   * 
   * @param key
   *          - String name of the saved recording
   * @param value
   *          - String calculations associated with the save key
   * 
   * @throws IOException
   *           the IOException
   */
  public void writeFile(final String key, final String value) throws IOException
  {

    String line = null;

    if (key != null && value != null)
    {
      open(1);
      line = key + "|" + value;

      write(line);

      close(1);
    }

  }

}
