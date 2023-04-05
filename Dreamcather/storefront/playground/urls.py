from django.urls import path
from . import views
from .views import duration_chart
#URL configuration
urlpatterns = [
    path('start/',views.index),
     path('duration-chart/', views.duration_chart, name='duration_chart'),
      path('stress_level-chart/', views.stress_level_chart, name='stress_level_chart'),
      path('chart-redirect/', views.chart_redirect, name='chart_redirect'),
        path('energy-chart/', views.energy_chart, name='energy_chart')

]