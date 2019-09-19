// +build !ignore_autogenerated

// This file was autogenerated by openapi-gen. Do not edit it manually!

package v1alpha1

import (
	spec "github.com/go-openapi/spec"
	common "k8s.io/kube-openapi/pkg/common"
)

func GetOpenAPIDefinitions(ref common.ReferenceCallback) map[string]common.OpenAPIDefinition {
	return map[string]common.OpenAPIDefinition{
		"github.com/RHsyseng/serverless-orchestration/serverless-workflow-operator/pkg/apis/app/v1alpha1.Workflow":       schema_pkg_apis_app_v1alpha1_Workflow(ref),
		"github.com/RHsyseng/serverless-orchestration/serverless-workflow-operator/pkg/apis/app/v1alpha1.WorkflowSpec":   schema_pkg_apis_app_v1alpha1_WorkflowSpec(ref),
		"github.com/RHsyseng/serverless-orchestration/serverless-workflow-operator/pkg/apis/app/v1alpha1.WorkflowStatus": schema_pkg_apis_app_v1alpha1_WorkflowStatus(ref),
	}
}

func schema_pkg_apis_app_v1alpha1_Workflow(ref common.ReferenceCallback) common.OpenAPIDefinition {
	return common.OpenAPIDefinition{
		Schema: spec.Schema{
			SchemaProps: spec.SchemaProps{
				Description: "Workflow is the Schema for the workflows API",
				Properties: map[string]spec.Schema{
					"kind": {
						SchemaProps: spec.SchemaProps{
							Description: "Kind is a string value representing the REST resource this object represents. Servers may infer this from the endpoint the client submits requests to. Cannot be updated. In CamelCase. More info: https://git.k8s.io/community/contributors/devel/api-conventions.md#types-kinds",
							Type:        []string{"string"},
							Format:      "",
						},
					},
					"apiVersion": {
						SchemaProps: spec.SchemaProps{
							Description: "APIVersion defines the versioned schema of this representation of an object. Servers should convert recognized schemas to the latest internal value, and may reject unrecognized values. More info: https://git.k8s.io/community/contributors/devel/api-conventions.md#resources",
							Type:        []string{"string"},
							Format:      "",
						},
					},
					"metadata": {
						SchemaProps: spec.SchemaProps{
							Ref: ref("k8s.io/apimachinery/pkg/apis/meta/v1.ObjectMeta"),
						},
					},
					"spec": {
						SchemaProps: spec.SchemaProps{
							Ref: ref("github.com/RHsyseng/serverless-orchestration/serverless-workflow-operator/pkg/apis/app/v1alpha1.WorkflowSpec"),
						},
					},
					"status": {
						SchemaProps: spec.SchemaProps{
							Ref: ref("github.com/RHsyseng/serverless-orchestration/serverless-workflow-operator/pkg/apis/app/v1alpha1.WorkflowStatus"),
						},
					},
				},
			},
		},
		Dependencies: []string{
			"github.com/RHsyseng/serverless-orchestration/serverless-workflow-operator/pkg/apis/app/v1alpha1.WorkflowSpec", "github.com/RHsyseng/serverless-orchestration/serverless-workflow-operator/pkg/apis/app/v1alpha1.WorkflowStatus", "k8s.io/apimachinery/pkg/apis/meta/v1.ObjectMeta"},
	}
}

func schema_pkg_apis_app_v1alpha1_WorkflowSpec(ref common.ReferenceCallback) common.OpenAPIDefinition {
	return common.OpenAPIDefinition{
		Schema: spec.Schema{
			SchemaProps: spec.SchemaProps{
				Description: "WorkflowSpec defines the desired state of Workflow",
				Properties: map[string]spec.Schema{
					"definition": {
						SchemaProps: spec.SchemaProps{
							Ref: ref("github.com/RHsyseng/serverless-orchestration/serverless-workflow-operator/pkg/apis/app/v1alpha1.Definition"),
						},
					},
					"image": {
						SchemaProps: spec.SchemaProps{
							Type:   []string{"string"},
							Format: "",
						},
					},
					"watch": {
						SchemaProps: spec.SchemaProps{
							Type:   []string{"boolean"},
							Format: "",
						},
					},
				},
				Required: []string{"definition"},
			},
		},
		Dependencies: []string{
			"github.com/RHsyseng/serverless-orchestration/serverless-workflow-operator/pkg/apis/app/v1alpha1.Definition"},
	}
}

func schema_pkg_apis_app_v1alpha1_WorkflowStatus(ref common.ReferenceCallback) common.OpenAPIDefinition {
	return common.OpenAPIDefinition{
		Schema: spec.Schema{
			SchemaProps: spec.SchemaProps{
				Description: "WorkflowStatus defines the observed state of Workflow",
				Properties: map[string]spec.Schema{
					"deployments": {
						SchemaProps: spec.SchemaProps{
							Ref: ref("github.com/RHsyseng/operator-utils/pkg/olm.DeploymentStatus"),
						},
					},
				},
				Required: []string{"deployments"},
			},
		},
		Dependencies: []string{
			"github.com/RHsyseng/operator-utils/pkg/olm.DeploymentStatus"},
	}
}
