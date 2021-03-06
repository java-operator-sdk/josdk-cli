package io.javaoperatorsdk.quarkus.resources;

import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import javax.inject.Singleton;
import org.jboss.logging.Logger;

public class ResourceBuildStep {
  static final Logger LOGGER = Logger.getLogger(ResourceBuildStep.class);

  public ResourceBuildStep() {}

  @BuildStep
  public FeatureBuildItem feature() {
    LOGGER.info("in feature...");
    return new FeatureBuildItem("resource-processor");
  }

  @BuildStep
  @Record(ExecutionTime.STATIC_INIT)
  public SyntheticBeanBuildItem buildItem(TestResourceRecorder testResourceRecorder) {
    LOGGER.info("in buildItem");
    SyntheticBeanBuildItem buildItem =
        SyntheticBeanBuildItem.configure(ResourceLocator.class)
            .scope(Singleton.class)
            .addType(ResourceLocator.class)
            .supplier(testResourceRecorder.getSupplier())
            .defaultBean()
            .done();

    return buildItem;
  }
}
