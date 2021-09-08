package com.github.ficat.plugintemplate.mytemplate

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider

class PluginTemplateProviderImpl : WizardTemplateProvider()  {
    override fun getTemplates(): List<Template> = listOf(
        MvpTemplate,
    )
}