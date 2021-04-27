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

  /**
   * Default Constructor.
   * 
   * @throws IOException
   *           - the IOException
   */
  private SaveHandler() throws IOException
  {
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
   * @param record
   *          what file to open.
   * @return true if successful
   * @throws IOException
   *           theIOException
   */
  private boolean open(final int which, final File record) throws IOException
  {

    boolean canOpen = false;

    if (which == 0)
    {
      if (record.exists())
      {
        canOpen = true;
        reader = new BufferedReader(new FileReader(record));

      } // end if

    }
    else
    {
      if (record.createNewFile())
      {
        writer = new PrintWriter(new FileWriter(record));

        if (record.canWrite())
        {
          canOpen = true;
        }

      }
    }

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
   * reads the file and returns the calculations stored.
   * 
   * @param record
   *          - which file to read.
   * @return String - the saved calculations, returns "" if file is invalid.
   * 
   * @throws IOException
   *           the IOException
   */
  public String readFile(final File record) throws IOException
  {

    String line = null;
    String value = "";

    if (open(0, record))
    {

      line = readLine();
      if (record.getName().contains("txt"))
      {
        while (line != null)
        {

          String[] fields = line.split("\\|");
          if (fields.length == 2)
          {
            value = fields[1];

            line = readLine();
          }
          else
            line = null;
        }
      }
      close(0);

    }
    return value;

  } // method readFiles

  /**
   * Adds a saved calculation and its key to a file. returns true if file was saved successfully.
   * 
   * @param key
   *          - String name of the saved recording
   * @param value
   *          - String calculations associated with the save key
   * @return Boolean - whether the file was saved successfully
   * 
   * @throws IOException
   *           the IOException
   */
  public Boolean writeFile(final String key, final String value) throws IOException
  {

    String line = null;
    Boolean saved = false;
    File newSave = new File(key + ".txt");

    if (key != null && value != null)
    {
      open(1, newSave);
      line = key + "|" + value;

      write(line);
      saved = true;

      close(1);
    }
    return saved;
  }

}
