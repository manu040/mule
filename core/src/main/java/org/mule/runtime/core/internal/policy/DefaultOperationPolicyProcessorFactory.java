/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.core.internal.policy;

import org.mule.runtime.core.api.policy.Policy;
import org.mule.runtime.core.api.policy.PolicyStateHandler;
import org.mule.runtime.core.api.processor.Processor;
import org.mule.runtime.core.api.processor.ReactiveProcessor;

/**
 * Default implementation for {@link OperationPolicyProcessorFactory}.
 *
 * @since 4.0
 */
public class DefaultOperationPolicyProcessorFactory implements OperationPolicyProcessorFactory {

  private final PolicyStateHandler policyStateHandler;

  /**
   * Creates a new {@link Processor} from an operation {@link Policy}.
   *
   * @param policyStateHandler the state handler to use for keeping track of the policy chain event modifications.
   */
  public DefaultOperationPolicyProcessorFactory(PolicyStateHandler policyStateHandler) {
    this.policyStateHandler = policyStateHandler;
  }

  @Override
  public ReactiveProcessor createOperationPolicy(Policy policy, ReactiveProcessor nextProcessor) {
    return new OperationPolicyProcessor(policy, policyStateHandler, nextProcessor);
  }
}
