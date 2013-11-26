#{% extends "base/configure.ac" %}
#{% block license %}
{{component.shellLicense}}

#{% endblock %}

#{######### Inherit acInit from redhawk base template #########}
#{######### Inherit acChecks from redhawk base template #########}

#{% block pkgConfigPath %}
{{super()}}":${SDRROOT}/dom/deps/gnuhawk/lib64/pkgconfig:${SDRROOT}/dom/deps/gnuhawk/lib/pkgconfig"
#{% endblock %}
#{% block projectDeps %}
{{super()}} gnuhawk >= 0.1.0#{% endblock %}

#{######### Inherit interfaceDeps from redhawk base template #########}
#{######### Inherit coreDeps from redhawk base template #########}
#{######### Inherit acConfig from redhawk base template #########}


