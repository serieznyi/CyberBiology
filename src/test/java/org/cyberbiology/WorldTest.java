package org.cyberbiology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

  private World world;

  @BeforeEach
  void setUp() {
    this.world = new World(1, 1);
  }

  @Test
  void getIteration() {
    assertEquals(0, this.world.getIteration());
  }
}