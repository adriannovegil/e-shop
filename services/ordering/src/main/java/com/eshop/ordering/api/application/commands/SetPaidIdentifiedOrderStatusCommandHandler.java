package com.eshop.ordering.api.application.commands;

import an.awesome.pipelinr.Pipeline;
import com.eshop.ordering.infrastructure.idempotency.RequestManager;

//@Component
public class SetPaidIdentifiedOrderStatusCommandHandler extends IdentifiedCommandHandler<SetPaidOrderStatusCommand, Boolean> {
  public SetPaidIdentifiedOrderStatusCommandHandler(Pipeline pipeline, RequestManager requestManager) {
    super(pipeline, requestManager);
  }

  @Override
  protected Boolean createResultForDuplicateRequest() {
    return true;  // Ignore duplicate requests for processing order.
  }
}
