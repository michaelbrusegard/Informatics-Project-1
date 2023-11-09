package workoutplanner.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;
import workoutplanner.core.User;
import workoutplanner.core.Workout;
import workoutplanner.json.internal.WorkoutplannerModule;

public class WorkoutplannerPersistence{

  public enum WorkoutplannerComponents{
    EXERCISES, WORKOUTS, User
  }
  private ObjectMapper objectMapper;

  private Path saveFilePath = null;

  public WorkoutplannerPersistence() {
    objectMapper = createObjectMapper();
  }

  public static SimpleModule createJacksonModule(final Set<WorkoutplannerComponents> components) {
    return new WorkoutplannerModule(components);
  }

  public static ObjectMapper createObjectMapper(final Set<WorkoutplannerComponents> components) {
    return  new ObjectMapper().registerModule(createJacksonModule(components));
  }

  public static ObjectMapper createObjectMapper() {
    return createObjectMapper(EnumSet.allOf(WorkoutplannerComponents.class));
  }

  public User readUser(final Reader reader) throws IOException {
    return objectMapper.readValue(reader, User.class);
  }

  public void writeUser(final User user, final Writer writer) throws IOException {
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, user);
  }

  public void setSaveFilePath(final String filePath) {
    saveFilePath = Paths.get(System.getProperty("user.home"), filePath);
  }

  public Path getSaveFilePath() {
    return saveFilePath;
  }

  public User loadUser() throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set yet");
    }
    try (Reader reader = new FileReader(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      System.out.println("looking for file in ");
      return readUser(reader);
    }

  }

  public void saveUser(final User user) throws IOException, IllegalStateException {
    System.out.println("saving to file");
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set yet");
    }
    try (Writer writer = new FileWriter(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      System.out.println("skriver til fil "+getSaveFilePath());
      writeUser(user, writer);
    }
    catch (IOException e){
      System.out.println(e.getMessage());
    }
  }


}
