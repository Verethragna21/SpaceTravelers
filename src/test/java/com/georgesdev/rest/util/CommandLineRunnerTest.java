package com.georgesdev.rest.util;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.georgesdev.rest.dao.IGalaxyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommandLineRunnerTest {

  @InjectMocks
  private CommandLineRunner commandLineRunner;

  @Mock
  private IGalaxyRepository repository;

  @Test
  public void run() throws Exception {
    commandLineRunner.run("args");

    verify(repository, atLeastOnce()).deleteAll();
    verify(repository, atLeast(3)).save(any());
  }

}